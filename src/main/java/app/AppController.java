//package app;
//
//import client_service.LocationGiver.DeepSeekClient;
//import client_service.GoogleMapsClient.GoogleMapsClient;
//import client_service.Recommendation.RecommendationService;
//import entity.Location;
//import entity.Recommendation;
//import io.github.cdimascio.dotenv.Dotenv;
//
//import java.util.List;
//
//public class AppController {
//    public List<Location> getRecommendations(String prompt) {
//        try {
//            DeepSeekClient dsclient = new DeepSeekClient(prompt);
//            dsclient.extractKeywords();
//
//            Dotenv dotenv = Dotenv.load();
//            final int radiusInMeters = 5000;
//            final String postalCode = "M5S 2E4";
//
//            GoogleMapsClient client = new GoogleMapsClient(radiusInMeters);
//            List<Location> locations = client.serveLocations(postalCode);
//
//            if (locations == null || locations.isEmpty()) return List.of();
//
//            RecommendationService recommendationService = new RecommendationService(dsclient);
//            Recommendation recommendation = recommendationService.recommend(locations, 5);
//
//            return recommendation.getLocations();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return List.of();
//        }
//    }
//}
