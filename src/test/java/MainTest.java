import app.AppController;
import client_service.recommendation.Recommender;
import client_service.api.DeepSeekClient;
import client_service.api.GoogleMapsClient;
import entity.Location;
import exceptions.APIException;
import interface_service.LLMClient;
import org.junit.jupiter.api.Test;
import view.LoginView;
import view.MapView;
import view.RecommendationView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class MainTest {
//    @Test
//    public void loginViewTest(){
//        final JFrame application = new JFrame("Login");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        application.setContentPane(new LoginView());
//        application.setSize(800, 400);
//        application.setLocationRelativeTo(null);
//        application.setVisible(true);
//    }

    int radiusInMeters = 5000;
    GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);

    private static List<Location> generateBackupLocations() {
        Location location1 = new Location("The Yorkville Royal Sonesta Hotel Toronto", (float) 43.653225,
                (float) -79.38319, new ArrayList<>(), "220 Bloor St W, Toronto, ON M5S 3B7, Canada");

        Location location2 = new Location("Madison Manor Boutique Hotel", (float) 43.668041,
                (float) -79.4035, new ArrayList<>(), "20 Madison Ave, Toronto, ON M5R 2S1, Canada");

        Location location3 = new Location("HOTEL OCHO", (float) 43.650005,
                (float) -79.39639, new ArrayList<>(), "195 Spadina Ave., Toronto, ON M5T 2C3, Canada");

        Location location4 = new Location("Art Gallery of Ontario", (float) 43.653606,
                (float) -79.39251, new ArrayList<>(), "317 Dundas St W, Toronto, ON M5T 1G4, Canada");

        Location location5 = new Location("Royal Ontario Museum", (float) 43.667709,
                (float) -79.394775, new ArrayList<>(), "100 Queens Park, Toronto, ON M5S 2C6, Canada");

        List<Location> backupLocations = new ArrayList<>();
        backupLocations.add(location1);
        backupLocations.add(location2);
        backupLocations.add(location3);
        backupLocations.add(location4);
        backupLocations.add(location5);
        return backupLocations;
    }

//    @Test
//    public void loginViewOnFindLocationsClick(){
//        LoginView loginView = new LoginView();
//        loginView.setUsernameField("Tasfia");
//        loginView.setPostalCodeField("M4B0C1");
//        loginView.setVibeField("happy");
//        loginView.findLocationButton.doClick();
//    }

//    @Test
//    public void recommendationViewNonEmptyTest(){
//        AppController controller = new AppController();
//        String postalCode = "M5B 0A5";
//        String vibe = "happy";
//        RecommendationView recommendationView = new RecommendationView(controller.getRecommendations(vibe, postalCode));
//        recommendationView.setVisible(true);
//        recommendationView.viewMapButton.doClick();
//
//    }

//    @Test
//    public void recommendationViewEmptyTest(){
//        RecommendationView recommendationView = new RecommendationView(List.of());
//        recommendationView.setVisible(true);
//        recommendationView.viewMapButton.doClick();
//    }

    @Test
    public void recommenderTest(){
        LLMClient ds = new DeepSeekClient();
        Recommender recommender = new Recommender("hello", generateBackupLocations(), ds);
        assert recommender.getLocations() != null;

    }

    @Test
    public void mapViewNonEmptyTest(){
        MapView mapView = new MapView(generateBackupLocations());
        mapView.setVisible(true);
    }

    @Test
    public void mapViewEmptyTest(){
        MapView mapView = new MapView(List.of());
        mapView.setVisible(true);
    }

    @Test
    public void gMapsPostalCodeWrongLength() throws exceptions.APIException {
        int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        assertThrows(APIException.class, () -> {
            gmaps.serveLocations("a");
        });
    }

    @Test
    public void gMapsPostalCodeWrongFormat() throws exceptions.APIException {
        int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        assertThrows(APIException.class, () -> {
            gmaps.serveLocations("aaaa");
        });
    }

    @Test
    public void gMapsInvalidPostalCode() throws exceptions.APIException {
        int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        assertThrows(APIException.class, () -> {
            gmaps.serveLocations("$%^()&");
        });
    }

//    @Test
//    public void appControllerPostalCodeWrongFormatThrowsException() throws Exception {
//        AppController controller = new AppController();
//        controller.getRecommendations("happy", "aaa");
//    }

    @Test
    public void deepSeekExceptionTest() throws exceptions.APIException{
        DeepSeekClient ds = new DeepSeekClient();
        ds.setAPI_KEY_TESTING("lalala");
        ds.getLLMResponse("happy");

    }

    @Test
    public void locationTest(){
        Location newloc = new Location("Bahen", 12.3f, 23.4f, List.of("cool"), "some address");
        newloc.setName("Myhal");
        newloc.setLatitude(56.7f);
        newloc.setLongitude(23.4f);
        newloc.setAddress("some NEW address");
        newloc.setReviews(List.of("very cool", "coolio"));
        assert newloc.getReviews().equals(List.of("very cool", "coolio"));
    }


}
