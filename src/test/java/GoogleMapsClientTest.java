import entity.Location;
import client_service.GoogleMapsClient.GoogleMapsClient;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class GoogleMapsClientTest {

    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.load();
        final String apiKey = dotenv.get("GOOGLE_MAPS_API_KEY");
        final int radiusInMeters = 5000;
        final String postalCode = "M5S 2E4";

        final GoogleMapsClient client = new GoogleMapsClient(radiusInMeters);

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
