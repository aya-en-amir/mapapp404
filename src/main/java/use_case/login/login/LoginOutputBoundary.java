package use_case.login.login;

/**
 * Output boundary for login.
 */
public interface LoginOutputBoundary {
    /**
     * Loads the success view for login
     * @param outputData the output data
     */
    void loadSuccessView(LoginOutputData outputData);

    /**
     * Load the failure view for the login
     * @param errorMessage the explanation of the failure
     */
    void loadFailView(String errorMessage);
}
