package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import interfaceadapter.login.LoginController;
import interfaceadapter.login.LoginState;
import interfaceadapter.login.LoginViewModel;
import interfaceadapter.recommendation.RecommendationController;

/**
 * The View for when the user is logging into the program.
 */

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Register";

    private final JTextField usernameField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JTextField postalCodeInputField = new JTextField(15);
    private final JLabel postalCodeErrorField = new JLabel();

    private final JTextField vibeField = new JTextField(15);
    private final JLabel vibeFieldError = new JLabel();
    private final LoginViewModel loginViewModel;
    private LoginController loginController;
    private RecommendationController recommendationController;

    private JButton findLocationButton;

    public LoginView(LoginViewModel loginViewModel) throws Exception {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(viewName);
        Font titleFont = new Font("SansSerif", Font.BOLD, 24);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(titleFont);

        Font labelFont = new Font("SansSerif", Font.PLAIN, 16);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);

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
        Font buttonFont = new Font("SansSerif", Font.BOLD, 18);
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

        findLocationButton.addActionListener(event -> {
            String username = usernameField.getText().trim();
            String postalCode = postalCodeInputField.getText().trim();
            String vibe = vibeField.getText().trim();
            if (username.isEmpty() || postalCode.isEmpty() || vibe.isEmpty()) {
                missingFieldsWarning();
            }
            findLocationButton.setEnabled(false);
            startFindLocationWorker(username, postalCode, vibe);
        });
    }

    private void missingFieldsWarning() {
        JOptionPane.showMessageDialog(this, "Please fill out all fields.",
                "Missing Information", JOptionPane.WARNING_MESSAGE);
    }

    private void startFindLocationWorker(String username, String postalCode, String vibe) {
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
    }

    /**
     * Sets the username field through the UI.
     *
     * @param username the user's username
     */
    public final void setUsernameField(String username) {
        this.usernameField.setText(username);
    }

    /**
     * Sets the postal code field through the UI.
     *
     * @param postalCode the user's postal code
     */
    public final void setPostalCodeField(String postalCode) {
        this.postalCodeInputField.setText(postalCode);
    }

    /**
     * Sets the vibe field through the UI.
     *
     * @param vibe the user's given vibe
     */
    public final void setVibeField(String vibe) {
        this.vibeField.setText(vibe);
    }

    public final void setRecommendationController(RecommendationController recommendationController) {
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

    public final String getViewName() {
        return viewName;
    }

    public final void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public final JButton getFindLocationButton() {
        return findLocationButton;
    }
}
