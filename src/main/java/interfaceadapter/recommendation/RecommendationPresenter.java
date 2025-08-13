package interfaceadapter.recommendation;

import java.util.List;

import interfaceadapter.ViewManagerModel;
import usecase.recommendation.RecommendationOutputBoundary;
import usecase.recommendation.RecommendationOutputData;

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
        state.setRecommendation(outputData.getRecoLocations());
        state.setErrorMessage(null);

        recommendationViewModel.setState(state);
        recommendationViewModel.firePropertyChanged();

        viewManagerModel.setState(recommendationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void loadFailureView(String errorMessage) {
        RecommendationState state = recommendationViewModel.getState();
        state.setRecommendation(List.of());
        state.setErrorMessage(errorMessage);

        recommendationViewModel.setState(state);
        recommendationViewModel.firePropertyChanged();

    }
}
