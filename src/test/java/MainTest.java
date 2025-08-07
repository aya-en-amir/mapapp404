import app.AppController;
import client_service.Recommendation.Recommender;
import client_service.api.DeepSeekClient;
import client_service.api.GoogleMapsClient;
import entity.Location;
import exceptions.InvalidPostalCodeException;
import interface_service.LLMClient;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import view.LoginView;
import view.MapView;
import view.RecommendationView;

import javax.swing.*;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class MainTest {
    @Test
    public void loginViewTest(){
        final JFrame application = new JFrame("Login");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setContentPane(new LoginView());
        application.setSize(800, 400);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }

    @Test
    public void loginViewOnFindLocationsClick(){
        LoginView loginView = new LoginView();
        loginView.setUsernameField("Tasfia");
        loginView.setPostalCodeField("M4B02");
        loginView.setVibeField("happy");
        loginView.findLocationButton.doClick();
    }

    @Test
    public void recommendationViewNonEmptyTest(){
        AppController controller = new AppController();
        String postalCode = "M5B 0A5";
        String vibe = "happy";
        RecommendationView recommendationView = new RecommendationView(controller.getRecommendations(vibe, postalCode));
        recommendationView.setVisible(true);
        recommendationView.viewMapButton.doClick();

    }

    @Test
    public void recommendationViewEmptyTest(){
        RecommendationView recommendationView = new RecommendationView(List.of());
        recommendationView.setVisible(true);
    }

    @Test
    public void recommenderTest(){
        int radiusInMeters = 5000;
        LLMClient ds = new DeepSeekClient();
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        Recommender recommender = new Recommender("hello", gmaps.getBackupLocationsTesting(), ds);
        assert recommender.getLocations() instanceof List;

    }

    @Test
    public void mapViewNonEmptyTest(){
        int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        MapView mapView = new MapView(gmaps.getBackupLocationsTesting());
        mapView.setVisible(true);
    }

    @Test
    public void mapViewEmptyTest(){
        MapView mapView = new MapView(List.of());
        mapView.setVisible(true);
    }

    @Test
    public void gMapsPostalCodeWrongLengthThrowsException() throws Exception {
        int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        gmaps.serveLocations("a");
    }

    @Test
    public void gMapsPostalCodeWrongFormatThrowsException() throws Exception {
        int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        gmaps.serveLocations("aaaa");
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
