package interfaceadapter.recommendation;

import interfaceadapter.ViewModel;

public class RecommendationViewModel extends ViewModel<RecommendationState> {

    public RecommendationViewModel() {
        super("recommendation");
        setState(new RecommendationState());
    }
}
