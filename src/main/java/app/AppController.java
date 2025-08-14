package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jetbrains.annotations.NotNull;

import data_access.InMemoryDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationPresenter;
import interface_adapter.recommendation.RecommendationViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInteractor;
import use_case.recommendation.RecommendationOutputBoundary;
import view.LoginView;

/**
 * The main app controller.
 */
public final class AppController {
    private final JPanel cardPanel = new JPanel();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final InMemoryDataAccessObject userDataAccessObject = new InMemoryDataAccessObject();
    private LoginViewModel loginViewModel = new LoginViewModel();
    private LoginView loginView;
    private RecommendationViewModel recommendationViewModel = new RecommendationViewModel();

    /**
     * Adds a new LoginView to the card panel using the current login view model.
     *
     * @return this controller
     * @throws Exception if the view cannot be created/added
     */
    public AppController addLoginView() throws Exception {
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Configures the LoginView with its required controllers.
     *
     * @return this controller for method chaining
     */
    public AppController addLoginUseCase() {
        final LoginController loginController = getLoginController();
        loginView.setLoginController(loginController);

        final RecommendationController recommendationController = getRecommendationController();

        loginView.setRecommendationController(recommendationController);

        return this;
    }

    /**
     * Builds and returns a configured LoginController.
     *
     * @return non-null login controller
     */
    private @NotNull LoginController getLoginController() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel,
                recommendationViewModel);

        final LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        return loginController;
    }

    /**
     * Builds and returns a configured RecommendationController.
     *
     * @return non-null recommendation controller
     */
    public @NotNull RecommendationController getRecommendationController() {
        final RecommendationOutputBoundary recommendationOutputBoundary =
                new RecommendationPresenter(recommendationViewModel, viewManagerModel);
        final RecommendationInputBoundary recommendationInteractor =
                new RecommendationInteractor(recommendationOutputBoundary);
        final RecommendationController recommendationController =
                new RecommendationController(recommendationInteractor);
        return recommendationController;
    }

    /**
     * Creates and displays the main application window.
     *
     * @return the configured JFrame
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setContentPane(cardPanel);
        application.setSize(800, 400);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
        return application;
    }

    /**
     * Returns the recommendation view model.
     *
     * @return non-null RecommendationViewModel
     */
    public RecommendationViewModel getRecommendationViewModel() {
        return recommendationViewModel;
    }
}
