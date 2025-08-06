package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.recommendation.RecommendationState;
import interface_adapter.recommendation.RecommendationViewModel;
import use_case.login.login.LoginOutputBoundary;
import use_case.login.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final RecommendationViewModel recommendationViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, RecommendationViewModel viewRecoModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.recommendationViewModel = viewRecoModel;
    }

    @Override
    public void loadSuccessView(LoginOutputData response) {
    }

    @Override
    public void loadFailView(String error) {
    }
}
