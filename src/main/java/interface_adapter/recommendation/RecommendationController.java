package interface_adapter.recommendation;

import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInputData;
import use_case.recommendation.RecommendationInteractor;

public class RecommendationController {
    final RecommendationInputBoundary recommendationInteractor;

    public RecommendationController(RecommendationInputBoundary recommendationInteractor) {
        this.recommendationInteractor = recommendationInteractor;
    }

    public void execute(String prompt, String postalCode) {
        final RecommendationInputData inputData = new RecommendationInputData(prompt, postalCode);
        recommendationInteractor.execute(inputData);
    }
}
