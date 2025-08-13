package interfaceadapter.login;

import java.util.List;

import entity.Recommendation;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.recommendation.RecommendationState;
import interfaceadapter.recommendation.RecommendationViewModel;
import usecase.login.LoginOutputBoundary;
import usecase.login.LoginOutputData;
import view.RecommendationView;

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
        List<Recommendation> recommendationList = recommendationViewModel.getState().getRecommendations();
        final RecommendationState recommendationState = recommendationViewModel.getState();
        recommendationState.setRecommendation(recommendationList);
        recommendationViewModel.setState(recommendationState);
        recommendationViewModel.firePropertyChanged();

        viewManagerModel.setState("recommendation");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToRecommendationView() {
        RecommendationView recommendationView = new RecommendationView(recommendationViewModel);
        recommendationView.setVisible(true);
    }
}
