import entity.Location;
import client_service.GoogleMapsClient.GoogleMapsClient;

import java.util.List;

public class GoogleMapsClientTest {

    public static void main(String[] args) throws Exception {
        final String apiKey = "";
        final int radiusInMeters = 5000;
        final String postalCode = "M5S 2E4";

        final GoogleMapsClient client = new GoogleMapsClient(apiKey, radiusInMeters);

        final List<Location> locations = client.serveLocations(postalCode);
        if (locations == null || locations.isEmpty()) {
            System.out.println("No locations found for the given postal code.");
        }
        else {
            System.out.println("Found " + locations.size() + " locations:");
            for (Location location : locations) {
                System.out.println(location);
            }
        }
    }
}
