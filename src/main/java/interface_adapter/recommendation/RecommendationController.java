package interface_adapter.recommendation;

import use_case.recommendation.RecommendationInputData;
import use_case.recommendation.RecommendationInteractor;

public class RecommendationController {
    final RecommendationInteractor recommendationInteractor;

    public RecommendationController(RecommendationInteractor recommendationInteractor) {
        this.recommendationInteractor = recommendationInteractor;
    }

    public void execute(String prompt, String postalCode) {
        final RecommendationInputData inputData = new RecommendationInputData(prompt, postalCode);
        recommendationInteractor.execute(inputData);
    }
}
