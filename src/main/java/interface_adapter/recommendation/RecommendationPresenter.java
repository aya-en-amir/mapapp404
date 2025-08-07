package interface_adapter.recommendation;

import interface_adapter.ViewManagerModel;
import use_case.recommendation.RecommendationOutputBoundary;
import use_case.recommendation.RecommendationOutputData;

import javax.swing.text.View;
import java.util.List;

public class RecommendationPresenter implements RecommendationOutputBoundary {
    private final RecommendationViewModel recommendationViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecommendationPresenter(RecommendationViewModel recommendationViewModel, ViewManagerModel view) {
        this.recommendationViewModel = recommendationViewModel;
        this.viewManagerModel = view;
    }

    @Override
    public void loadSuccessView(RecommendationOutputData outputData) {
        RecommendationState state = recommendationViewModel.getState();
        state.setRecoLocations(outputData.getRecoLocations());
        state.setErrorMessage(null);

        recommendationViewModel.setState(state);
        recommendationViewModel.firePropertyChanged();

        viewManagerModel.setState(recommendationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void loadFailureView(String errorMessage) {
        RecommendationState state = recommendationViewModel.getState();
        state.setRecoLocations(List.of());
        state.setErrorMessage(errorMessage);

        recommendationViewModel.setState(state);
        recommendationViewModel.firePropertyChanged();

    }
}
