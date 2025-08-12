package app;

import data_access.InMemoryDataAccessObject;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationPresenter;
import interface_adapter.recommendation.RecommendationViewModel;

import org.jetbrains.annotations.NotNull;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInteractor;
import use_case.recommendation.RecommendationOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.awt.*;

public class AppController {
    private final JPanel cardPanel = new JPanel();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final InMemoryDataAccessObject userDataAccessObject = new InMemoryDataAccessObject();
    private LoginViewModel loginViewModel = new LoginViewModel();
    private LoginView loginView;
    private RecommendationViewModel recommendationViewModel = new RecommendationViewModel();


    public AppController addLoginView() {
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    public AppController addLoginUseCase() {
        final LoginController loginController = getLoginController();
        loginView.setLoginController(loginController);

        final RecommendationController recommendationController = getRecommendationController();

        loginView.setRecommendationController(recommendationController);

        return this;
    }

    private @NotNull LoginController getLoginController() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel,
                recommendationViewModel);

        final LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        return loginController;
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
        final JFrame application = new JFrame("Login");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setContentPane( cardPanel );
        application.setSize(800, 400);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
        return application;
    }
}


