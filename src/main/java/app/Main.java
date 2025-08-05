package app;



import view.LoginView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {

        final JFrame application = new JFrame("Login");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setContentPane(new LoginView());
        application.setSize(800, 400);
        application.setLocationRelativeTo(null);
        application.setVisible(true);

//        final CardLayout cardLayout = new CardLayout();
//
//        final JPanel views = new JPanel(cardLayout);
//        application.add(views);
//
//        LoginView loginView = new LoginView();
//        views.add(loginView);
//
//        application.pack();
//        application.setVisible(true);

    }
}