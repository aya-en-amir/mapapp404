package use_case.login;

/**
 * Input boundary for login.
 */
public interface LoginInputBoundary {

    /**
     * Implement the login use case.
     * @param loginInputData input data for login
     */
    void execute(LoginInputData loginInputData);
}

