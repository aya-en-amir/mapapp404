package usecase.login;

/**
 * Output boundary for login.
 */
public interface LoginOutputBoundary {
    /**
     * Loads the success view for login.
     * @param outputData the output data
     */
    void loadSuccessView(LoginOutputData outputData);

    /**
     * Switches to the Login View.
     */
    void switchToRecommendationView();
}
