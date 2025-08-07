package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final RecommendationViewModel recommendationViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                          RecommendationViewModel recommendationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.recommendationViewModel = recommendationViewModel;
    }

    @Override
    public void loadSuccessView(LoginOutputData response) {
        viewManagerModel.setState("recommendation");
        viewManagerModel.firePropertyChanged();
    }

}
