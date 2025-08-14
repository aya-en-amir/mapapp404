package app;

import javax.swing.JFrame;

public class Main {
    /**
     * Application entry point. Initializes and displays the login interface.
     *
     * @param args command-line arguments
     * @throws Exception if initialization fails
     */
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
