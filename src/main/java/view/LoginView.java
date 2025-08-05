package view;

import app.AppController;

import javax.swing.*;
import java.awt.*;

/**
 * The View for when the user is logging into the program.
 */

public class LoginView extends JPanel{

    final String viewName = "Log In";

    private final JTextField usernameField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JTextField postalCodeInputField = new JTextField(15);
    private final JLabel postalCodeErrorField = new JLabel();

    private final JTextField vibeField = new JTextField(15);
    private final JLabel vibeFieldError = new JLabel();

    private final JButton findLocationButton;

    public LoginView() {

        // Set bigger fonts
        Font labelFont = new Font("SansSerif", Font.PLAIN, 16);   // labels
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);   // text fields
        Font buttonFont = new Font("SansSerif", Font.BOLD, 18);   // button
        Font titleFont = new Font("SansSerif", Font.BOLD, 24);    // title

        final JLabel title = new JLabel(viewName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(titleFont);

        JLabel usernameLabel = new JLabel("Enter your username: e.g Alice");
        usernameLabel.setFont(labelFont);
        usernameField.setFont(fieldFont);
        usernameField.setPreferredSize(new Dimension(300, 30));

        JLabel postalCodeLabel = new JLabel("Enter your postal code: e.g A0B 0C0");
        postalCodeLabel.setFont(labelFont);
        postalCodeInputField.setFont(fieldFont);
        postalCodeInputField.setPreferredSize(new Dimension(300, 30));

        JLabel vibeLabel = new JLabel("Enter the vibe you're feeling today e.g gloomy");
        vibeLabel.setFont(labelFont);
        vibeField.setFont(fieldFont);
        vibeField.setPreferredSize(new Dimension(300, 30));

        usernameErrorField.setFont(labelFont);
        postalCodeErrorField.setFont(labelFont);
        vibeFieldError.setFont(labelFont);

        final JPanel buttons = new JPanel();
        findLocationButton = new JButton("Find Locations");
        findLocationButton.setFont(buttonFont);
        findLocationButton.setSize(60, 45);
        buttons.add(findLocationButton);



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new LabelTextPanel(usernameLabel, usernameField));
        this.add(new LabelTextPanel(postalCodeLabel, postalCodeInputField));
        this.add(new LabelTextPanel(vibeLabel, vibeField));
        this.add(usernameErrorField);
        this.add(postalCodeErrorField);
        this.add(vibeFieldError);
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
            RecommendationView recommendationView = new RecommendationView(controller.getRecommendations(vibe, postalCode));
            recommendationView.setVisible(true);
        });
    }
}