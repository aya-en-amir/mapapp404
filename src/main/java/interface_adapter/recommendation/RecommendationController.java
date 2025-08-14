package interface_adapter.recommendation;

import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInputData;

public class RecommendationController {
    private final RecommendationInputBoundary recommendationInteractor;

    public RecommendationController(RecommendationInputBoundary recommendationInteractor) {
        this.recommendationInteractor = recommendationInteractor;
    }

    /**
     * Executes the recommendation use case with the given prompt and postal code.
     *
     * @param prompt     the user prompt
     * @param postalCode the postal code to search within
     * @throws Exception if execution fails
     */
    public final void execute(String prompt, String postalCode) throws Exception {
        final RecommendationInputData inputData = new RecommendationInputData(prompt, postalCode);
        recommendationInteractor.execute(inputData);
    }
}
