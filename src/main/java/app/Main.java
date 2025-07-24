package app;

import client_service.DeepSeekClient.DeepSeekClient;
import client_service.GoogleMapsClient.GoogleMapsClient;
import entity.Location;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DeepSeekClient dsclient = new DeepSeekClient("I am stressed and anxious");
        dsclient.extractKeywords();

        // maps
        Dotenv dotenv = Dotenv.load();
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