package app;



import view.LoginView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {

        final AppController appBuilder = new AppController();
        final JFrame application = appBuilder
                .addLoginView()
                .addLoginUseCase()
                .build();

        application.pack();
        application.setVisible(true);

    }
}