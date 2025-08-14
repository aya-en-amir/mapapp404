import app.AppController;
import client_service.recommendation.Recommender;
import client_service.api.DeepSeekClient;
import client_service.api.GoogleMapsClient;
import entity.Location;
import entity.Recommendation;
import entity.User;
import exceptions.ApiException;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.recommendation.RecommendationState;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_service.LlmClient;
import org.junit.jupiter.api.Test;
import view.LoginView;
import view.MapView;
import view.RecommendationView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class MainTest {
//    @Test
//    public void loginViewTest() throws Exception{
//        final JFrame application = new JFrame("Login");
//        final LoginViewModel loginViewModel = new LoginViewModel();
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        application.setContentPane(new LoginView(loginViewModel));
//        application.setSize(800, 400);
//        application.setLocationRelativeTo(null);
//        application.setVisible(true);
//    }

    @Test
    public void loginView() throws Exception {
        final AppController appBuilder = new AppController();
        final JFrame application = appBuilder
                .addLoginView()
                .addLoginUseCase()
                .build();
        application.pack();
        application.setVisible(true);
    }

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

    @Test
    public void loginViewOnFindLocationsClick() throws Exception {
        final LoginViewModel loginViewModel = new LoginViewModel();
        LoginView loginView = new LoginView(loginViewModel);
        loginView.setUsernameField("Tasfia");
        loginView.setPostalCodeField("M4B0C1");
        loginView.setVibeField("happy");
        loginView.getFindLocationButton().doClick();
    }

    @Test
    public void recommendationViewEmptyTest(){
        RecommendationView recommendationView = new RecommendationView(new RecommendationViewModel());
        recommendationView.setVisible(true);
        assert (recommendationView.getViewMapButton() == null);
    }

    @Test
    public void recommendationViewNonEmptyTest(){
        RecommendationViewModel recViewModel = new RecommendationViewModel();
        //Recommender recommender = new Recommender("happy", generateBackupLocations(), ds);
//        List<Location> locations = generat
        Recommendation recommendation = new Recommendation(generateBackupLocations());
        List<Recommendation> recommendationList = new ArrayList<>();
        recommendationList.add(recommendation);
        RecommendationState recommendationState = new RecommendationState();
        recommendationState.setRecommendation(recommendationList);
        recViewModel.setState(recommendationState);
        RecommendationView recommendationView = new RecommendationView(recViewModel);
        recommendationView.setVisible(true);
    }

    @Test
    public void recommenderTestBackupLocations(){
        LlmClient ds = new DeepSeekClient();
        Recommender recommender = new Recommender("hello", generateBackupLocations(), ds);
        assert recommender.getLocations() != null;

    }

    @Test
    public void recommenderTestLLMParser(){
        LlmClient ds = new DeepSeekClient();
        Recommender recommender = new Recommender("happy", generateBackupLocations(), ds);
        assert recommender.recommend() != null;
    }

    @Test
    public void LoginStateTestUsernameTest(){
        LoginState loginState = new LoginState();
        loginState.setUsername("Tasfia") ;
        assert Objects.equals(loginState.getUsername(), "Tasfia");

    }

    @Test
    public void LoginStateTestSetLoginError(){
        LoginState loginState = new LoginState();
        loginState.setUsername("Tasfia") ;
        loginState.setLoginError("This user doesn't exist");
        assert(loginState.getLoginError() != null);
    }

    @Test
    public void LoginStatePostalCodeTest(){
        LoginState loginState = new LoginState();
        assert(loginState.getPostalCode() != null);

    }

    @Test
    public void UserTest(){
        User newUser = new User("Tasfia", "Blablabla");

        assert (newUser.getUserName().equals("Tasfia"));
        assert (newUser.getPostalCode().equals("Blablabla"));

        newUser.setPostalCode("A0B 0C0");
        assert (newUser.getPostalCode().equals("A0B 0C0"));
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
    public void gMapsPostalCodeWrongLength() throws ApiException {
        int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        assertThrows(Exception.class, () -> {
            gmaps.serveLocations("a");
        });
    }

    @Test
    public void gMapsPostalCodeWrongFormat() throws ApiException {
        int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        assertThrows(Exception.class, () -> {
            gmaps.serveLocations("aaaa");
        });
    }

    @Test
    public void gMapsInvalidPostalCode() throws ApiException {
        int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        assertThrows(Exception.class, () -> {
            gmaps.serveLocations("$%^()&");
        });
    }

    @Test
    public void appControllerPostalCodeWrongFormatThrowsException() {
        AppController controller = new AppController();
        controller.getRecommendationViewModel();
    }

    @Test
    public void deepSeekExceptionTest() throws ApiException {
        DeepSeekClient ds = new DeepSeekClient();
        ds.setApiKeyTesting("lalala");
        ds.getLlmResponse("happy");

    }

    @Test
    public void locationTest(){
        Location newloc = new Location("Bahen", 12.3f, 23.4f, List.of("cool"), "some address");
        newloc.setName("Myhal");
        newloc.setLatitude(56.7f);
        newloc.setLongitude(23.4f);
        newloc.setAddress("some NEW address");
        newloc.setReviews(List.of("very cool", "coolio"));
        assert(newloc.getAddress() != null);
        assert newloc.getReviews().equals(List.of("very cool", "coolio"));
    }

    @Test
    public void recommendationTest(){
        List<Location> locations = generateBackupLocations();
        Recommendation recommendation = new Recommendation(locations);
        assert recommendation.getLocations() != null;
    }


}
