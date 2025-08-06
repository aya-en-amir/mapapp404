import app.AppController;
import client_service.Recommendation.Recommender;
import client_service.api.GoogleMapsClient;
import entity.Location;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import view.LoginView;
import view.RecommendationView;

import javax.swing.*;
import java.util.List;

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
    public void recommendationViewNonEmptyTest(){
        AppController controller = new AppController();
        String postalCode = "M5B 0A5";
        String vibe = "happy";
        RecommendationView recommendationView = new RecommendationView(controller.getRecommendations(vibe, postalCode));
        recommendationView.setVisible(true);
    }

    @Test
    public void mapViewTest(){

    }
}
