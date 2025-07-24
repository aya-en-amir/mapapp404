package app;

import client_service.DeepSeekClient.DeepSeekClient;
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
    }
}