package app;

import client_service.DeepSeekClient.DeepSeekClient;
tasfia.ara
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import view.LoginView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
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


main
    }
}