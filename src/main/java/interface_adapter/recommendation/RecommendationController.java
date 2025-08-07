package interface_adapter.recommendation;

import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInputData;
import use_case.recommendation.RecommendationInteractor;

public class RecommendationController {
    final RecommendationInputBoundary recommendationInputBoundary;

    public RecommendationController(RecommendationInputBoundary recommendationInputBoundary) {
        this.recommendationInputBoundary = recommendationInputBoundary;
    }

    public void execute(String prompt, String postalCode) {
        final RecommendationInputData inputData = new RecommendationInputData(prompt, postalCode);
        recommendationInputBoundary.execute(inputData);
    }
}
