package client_service.GoogleMapsClient;

import entity.Location;
import interface_service.LocationFinder;
import org.json.JSONArray;
import org.json.JSONObject;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Finds nearest locations using Google Maps Places API.
 */
public class GoogleMapsClient implements LocationFinder {
    private final String apiKey;
    private final int meterRadius;

    public GoogleMapsClient(int meterRadius) {
        final Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("GOOGLE_MAPS_API_KEY");
        this.meterRadius = meterRadius;
    }

    @Override
    public List<Location> serveLocations(String postalCode) throws Exception {
        final String encodedPostal = URLEncoder.encode(postalCode + ", Canada", "UTF-8");
        final String geocodeUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="
                + encodedPostal + "&key=" + apiKey;
        final JSONObject geoResponse = getJsonResponse(geocodeUrl);

        final JSONArray results = geoResponse.getJSONArray("results");

        List<Location> locations = new ArrayList<>();

        if (results.isEmpty()) {
            locations = null;
        }
        else {
            final JSONObject startLocation = results.getJSONObject(0);
            final float[] startCoordinates = findLatLon(startLocation);
            final float startLatitude = startCoordinates[0];
            final float startLongitude = startCoordinates[1];

            final List<JSONObject> rawPlaces = nearbyLocations(startLatitude, startLongitude);
            for (JSONObject rawLocation : rawPlaces) {
                locations.add(initializeLocation(rawLocation));
            }
        }
        return locations;
    }

    /**
     * Returns 20 nearby locations to a given coordinate.
     * @param lat the latitude
     * @param lon the longitude
     * @return a list of raw locations in JSONObject form
     */
    private List<JSONObject> nearbyLocations(double lat, double lon) throws Exception {
        final List<JSONObject> results = new ArrayList<>();
        final String url = String.format(
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%d&key=%s",
                lat, lon, meterRadius, apiKey
        );

        final JSONObject response = getJsonResponse(url);
        final List<JSONObject> nearbyPlaces = toList(response.getJSONArray("results"));
        for (JSONObject place : nearbyPlaces) {
            String placeId = place.optString("place_id", null);
            if (placeId != null) {
                JSONObject detailed = fetchDetails(placeId);
                if (detailed != null) {
                    results.add(detailed);
                }
            }
        }
        return results;
    }

    private JSONObject fetchDetails(String placeId) throws Exception {
        final String url = String.format(
                "https://maps.googleapis.com/maps/api/place/details/json?place_id=%s&fields=name,geometry,reviews&key=%s",
                placeId, apiKey
        );
        JSONObject response = getJsonResponse(url);
        JSONObject result = response.optJSONObject("result");
        return result;
    }

    /**
     * Initialize a location object.
     * @param rawLocation the location in JSONObject form
     * @return a Location object for the same location
     */
    public Location initializeLocation(JSONObject rawLocation) {
        final String name = findName(rawLocation);
        final float[] coordinates = findLatLon(rawLocation);
        final List<String> reviews = findReviews(rawLocation);
        return new Location(name, coordinates[0], coordinates[1], reviews);
    }

    /**
     * Find the latitude and longitude of a location.
     * @param rawLocation the location in JSONObject form
     * @return an array containing the latitude and longitude in that order.
     */
    private float[] findLatLon(JSONObject rawLocation) {
        final JSONObject location = rawLocation.getJSONObject("geometry").getJSONObject("location");
        return new float[]{location.getFloat("lat"), location.getFloat("lng")};
    }

    /**
     * Get a location's name.
     * @param rawLocation the location in JSONObject form
     * @return the name of the location
     */
    private String findName(JSONObject rawLocation) {
        return rawLocation.optString("name", "vicinity");
    }

    /**
     * Get the reviews of a location.
     * @param rawLocation the location in JSONObject form
     * @return a list of reviews as strings
     */
    private List<String> findReviews(JSONObject rawLocation) {
        final List<String> reviews = new ArrayList<>();
        if (rawLocation.has("reviews")) {
            final JSONArray jsonReviews = rawLocation.getJSONArray("reviews");
            for (int i = 0; i < jsonReviews.length(); i++) {
                final JSONObject review = jsonReviews.getJSONObject(i);
                reviews.add(review.optString("text", ""));
            }
        }
        return reviews;
    }

    /**
     * Extracts a JSONObject from a request response.
     * @param urlStr the response url
     * @return a JSONObject representing the request response
     */
    private JSONObject getJsonResponse(String urlStr) throws Exception {
        final URL url = new URL(urlStr);
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        final InputStream inputStream = conn.getInputStream();
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        return new JSONObject(sb.toString());
    }

    /**
     * Turns a JSONArray into a list of JSONObjects.
     * @param array the JSONArray
     * @return a list of JSONObjects from the array
     */
    private List<JSONObject> toList(JSONArray array) {
        final List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getJSONObject(i));
        }
        return list;
    }

}
