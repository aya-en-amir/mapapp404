package app;

import client_service.DeepSeekClient.DeepSeekClient;
import client_service.GoogleMapsClient.GoogleMapsClient;
import client_service.LocationGiver.LocationGiver;
import client_service.Recommendation.RecommendationService;
import entity.Location;
import entity.Recommendation;
import io.github.cdimascio.dotenv.Dotenv;
import view.LoginView;
import view.RecommendationView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppController {
    public List<Location> getRecommendationsCosineSimilarity(String prompt) {
        try {
            DeepSeekClient dsclient = new DeepSeekClient(prompt);
            dsclient.extractKeywords();

            Dotenv dotenv = Dotenv.load();
            final int radiusInMeters = 5000;
            final String postalCode = "M5S 2E4";

            GoogleMapsClient client = new GoogleMapsClient(radiusInMeters);
            List<Location> locations = client.serveLocations(postalCode);

            if (locations == null || locations.isEmpty()) return List.of();

            RecommendationService recommendationService = new RecommendationService(dsclient);
            Recommendation recommendation = recommendationService.recommend(locations, 5);

            return recommendation.getLocations();

        }
        catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
    public List<Location> getRecommendationLocationGiver(String prompt) {
        // mock location giver
        try{
            DeepSeekClient dsclient = new DeepSeekClient(prompt);
            dsclient.extractKeywords();

            Dotenv dotenv = Dotenv.load();
            final int radiusInMeters = 5000;
            final String postalCode = "M5S 2E4";

            GoogleMapsClient client = new GoogleMapsClient(radiusInMeters);
            List<Location> locations = client.serveLocations(postalCode);

            if (locations == null || locations.isEmpty()) return List.of();

            LocationGiver recommendedLocations = new LocationGiver(prompt, locations);
            return recommendedLocations.getLocations();
        }
        catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }



    }
}