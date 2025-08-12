package view;

import app.AppController;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.recommendation.RecommendationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logging into the program.
 */

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    final String viewName = "Register";

    private final JTextField usernameField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JTextField postalCodeInputField = new JTextField(15);
    private final JLabel postalCodeErrorField = new JLabel();

    private final JTextField vibeField = new JTextField(15);
    private final JLabel vibeFieldError = new JLabel();
    private final LoginViewModel loginViewModel;
    private LoginController loginController;
    private RecommendationController recommendationController;

    public JButton findLocationButton;

    public LoginView(LoginViewModel loginViewModel) throws Exception {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);


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

        JLabel postalCodeLabel = new JLabel("Enter your postal code: e.g M5S 2E4");
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
            String username = usernameField.getText().trim();
            String postalCode = postalCodeInputField.getText().trim();
            String vibe = vibeField.getText().trim();

            if (username.isEmpty() || postalCode.isEmpty() || vibe.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill out all fields.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            findLocationButton.setEnabled(false);

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    loginController.execute(username, postalCode);
                    recommendationController.execute(vibe, postalCode);
                    return null;
                }

                @Override
                protected void done() {
                    findLocationButton.setEnabled(true);
                    loginController.switchToRecommendationView();
                }
            };
            worker.execute();
        });

    }

    public void setUsernameField(String username) {
        this.usernameField.setText(username);
    }

    public void setPostalCodeField(String postalCode) {
        this.postalCodeInputField.setText(postalCode);
    }

    public void setVibeField(String vibe) {
        this.vibeField.setText(vibe);
    }

    public void setRecommendationController(RecommendationController recommendationController) {
        this.recommendationController = recommendationController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        usernameField.setText(state.getUsername());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
