package app;


import client_service.Recommendation.Recommender;
import client_service.api.DeepSeekClient;
import client_service.api.GoogleMapsClient;
import entity.Location;
import entity.Recommendation;
import interface_service.LLMClient;
import interface_service.RecommenderInterface;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class AppController {
    public List<Location> getRecommendations(String prompt) {
        try {
            LLMClient llmClient = new DeepSeekClient();
            final int radiusInMeters = 5000;
            final String postalCode = "M5S 2E4";

            GoogleMapsClient client = new GoogleMapsClient(radiusInMeters);
            List<Location> locations = client.serveLocations(postalCode);

            RecommenderInterface recommender = new Recommender(prompt, locations, llmClient);
            Recommendation recommendation = recommender.recommend();

            return recommendation.getLocations();

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
