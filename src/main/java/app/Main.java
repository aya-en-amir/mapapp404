package app;

import view.LoginView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {

        final JFrame application = new JFrame("Login");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();

        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        LoginView loginView = new LoginView();
        views.add(loginView);

        application.pack();
        application.setVisible(true);

//        Deepseek testing
//        DeepSeekClient dsclient = new DeepSeekClient("I am stressed and anxious");
//        dsclient.extractKeywords();

import client_service.DeepSeekClient.DeepSeekClient;
import client_service.GoogleMapsClient.GoogleMapsClient;
import client_service.Recommendation.RecommendationService;
import entity.Location;
import entity.Recommendation;
import io.github.cdimascio.dotenv.Dotenv;
import view.RecommendationView;

import java.util.Collections;
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

        List<Recommendation> recommendations;
        if (locations == null || locations.isEmpty()) {
            System.out.println("No locations found for the given postal code.");
            return;
        } else {
            // RecommendationService to find the recommended locations
            RecommendationService recommendationService = new RecommendationService(dsclient);
            recommendations = Collections.singletonList(recommendationService.recommend(locations, 5));

            for (Recommendation rec : recommendations) {
                System.out.println(rec.getLocations());
            }

        }
        new RecommendationView(recommendations);
        main

    }
}