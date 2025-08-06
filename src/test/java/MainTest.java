import app.AppController;
import client_service.api.GoogleMapsClient;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import view.LoginView;

import javax.swing.*;

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

    public void recommendationViewTest(){
        final int radiusInMeters = 5000;
        GoogleMapsClient gmaps = new GoogleMapsClient(radiusInMeters);
        final String postalCode = "M5S2E4";

    }
}
