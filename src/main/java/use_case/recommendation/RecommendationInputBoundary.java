package use_case.recommendation;

/**
 * Input boundary for login.
 */
public interface RecommendationInputBoundary {
    /**
     * Implement the recommendation use case.
     * @param inputData input data for login
     * @throws Exception if recommendation fails
     */
    void execute(RecommendationInputData inputData) throws Exception;
}
