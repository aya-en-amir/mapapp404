package usecase.recommendation;

/**
 * Output boundary for recommendation.
 */
public interface RecommendationOutputBoundary {
    /**
     * Loads the success view for recommendation.
     * @param outputData the output data
     */
    void loadSuccessView(RecommendationOutputData outputData);

    /**
     * Loads the failure view for recommendation.
     * @param errorMessage the error message
     */
    void loadFailureView(String errorMessage);
}
