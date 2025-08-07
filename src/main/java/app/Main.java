package app;



import view.LoginView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {

//        final JFrame application = new JFrame("Login");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        final CardLayout cardLayout = new CardLayout();
//
//        final JPanel views = new JPanel(cardLayout);
//        application.add(views);
//
//        LoginView loginView = new LoginView();
//        views.add(loginView);

        final AppController appBuilder = new AppController();
        final JFrame application = appBuilder
                .addLoginView()
                .addRecommendationView()
                .addLoginUseCase()
                .addRecommendationUseCase()
                .build();

        application.pack();
        application.setVisible(true);

    }
}