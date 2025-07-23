package app;

import client_service.DeepSeekClient.DeepSeekClient;
import client_service.GoogleMapsClient.GoogleMapsClient;
import client_service.Recommendation.RecommendationService;
import entity.Location;
import entity.Recommendation;
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
            // RecommendationService to find the recommended locations
            RecommendationService recommendationService = new RecommendationService(dsclient);
            Recommendation recommendation = recommendationService.recommend(locations, 5); // Top 5

            System.out.println("Found " + recommendation.getLocations() + " locations:");
            for (Location location : recommendation.getLocations()) {
                System.out.println(location);
            }
        }


    }
}