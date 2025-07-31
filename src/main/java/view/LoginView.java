package view;

import app.AppController;

import javax.swing.*;
import java.awt.*;

/**
 * The View for when the user is logging into the program.
 */

public class LoginView extends JPanel{

    final String viewName = "log in";
    // private final LoginViewModel loginViewModel;

    private final JTextField usernameField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JTextField postalCodeInputField = new JTextField(15);
    private final JLabel postalCodeErrorField = new JLabel();

    private final JTextField vibeField = new JTextField(15);
    private final JLabel vibeFieldError = new JLabel();

    private final JButton findLocationButton;
//    private final JButton cancel;

    public LoginView() {

        final JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Enter your username: e.g Alice"), usernameField);
        final LabelTextPanel postalCodeInfo = new LabelTextPanel(
                new JLabel("Enter your postal code: e.g A0B 0C0"), postalCodeInputField);
        final LabelTextPanel vibeInfo = new LabelTextPanel(
                new JLabel("Enter the vibe you're feeling today e.g gloomy"), vibeField);

        final JPanel buttons = new JPanel();
        findLocationButton = new JButton("Find Locations");
        buttons.add(findLocationButton);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(postalCodeInfo);
        this.add(vibeInfo);
        this.add(usernameErrorField);
        this.add(postalCodeErrorField);
        this.add(vibeFieldError);
        this.add(findLocationButton);
        this.add(buttons);

        findLocationButton.addActionListener(e -> {
            String username = usernameField.getText();
            String postalCode = postalCodeInputField.getText();
            String vibe = vibeField.getText();

            if (username.isEmpty() || postalCode.isEmpty() || vibe.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill out all fields.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            AppController controller = new AppController();
            controller.getRecommendations(vibe);
        });
    }



}

