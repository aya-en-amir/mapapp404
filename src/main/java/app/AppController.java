package app;


import client_service.Recommendation.Recommender;
import client_service.api.DeepSeekClient;
import client_service.api.GoogleMapsClient;
import data_access.InMemoryDataAccessObject;
import entity.Location;
import entity.Recommendation;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationPresenter;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_service.LLMClient;
import interface_service.RecommenderInterface;
import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.NotNull;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInteractor;
import use_case.recommendation.RecommendationOutputBoundary;
import view.LoginView;
import view.RecommendationView;
//import view.ResultView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AppController {
    private final JFrame application = new JFrame("Login");
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final InMemoryDataAccessObject userDataAccessObject = new InMemoryDataAccessObject();
    private LoginViewModel loginViewModel;
    private LoginView loginView;
    private RecommendationViewModel recommendationViewModel;
    private RecommendationView recommendationView;
//    private ResultView resultView;

    public AppController() {
        cardPanel.setLayout(cardLayout);
    }

    public AppController addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    public AppController addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel,
                recommendationViewModel);

        final LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);

        final RecommendationController recommendationController = getRecommendationController();
        loginView.setRecommendationController(recommendationController);

        return this;
    }

    public AppController addRecommendationView () {
        recommendationViewModel = new RecommendationViewModel();
        recommendationView = new RecommendationView(recommendationViewModel);
        cardPanel.add(recommendationView, recommendationView.getName());
        return this;
    }

    public AppController addRecommendationUseCase() {
        final RecommendationController recommendationController = getRecommendationController();
        recommendationView.setRecommendationController(recommendationController);

        return this;

    }

    private @NotNull RecommendationController getRecommendationController() {
        final RecommendationOutputBoundary recommendationOutputBoundary = new RecommendationPresenter(recommendationViewModel,
                viewManagerModel);
        final RecommendationInputBoundary recommendationInteractor = new RecommendationInteractor(userDataAccessObject,
                recommendationOutputBoundary);
        final RecommendationController recommendationController = new RecommendationController(recommendationInteractor);
        return recommendationController;
    }

    public JFrame build() {
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(loginView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}

//    public List<Location> getRecommendations(String prompt) {
//        try {
//            LLMClient llmClient = new DeepSeekClient();
//            final int radiusInMeters = 5000;
//            final String postalCode = "M5S 2E4";
//
//            GoogleMapsClient client = new GoogleMapsClient(radiusInMeters);
//            List<Location> locations = client.serveLocations(postalCode);
//
//            if (locations == null || locations.isEmpty()) return List.of();
//
//            RecommenderInterface recommender = new Recommender(prompt, locations, llmClient);
//            Recommendation recommendation = recommender.recommend();
//
//            return recommendation.getLocations();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return List.of();
//        }
//    }

