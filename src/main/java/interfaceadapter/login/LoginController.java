package interfaceadapter.login;

import usecase.login.LoginInputBoundary;
import usecase.login.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    /**
     * Finds nearby locations given a postal code.
     *
     * @param username the username of the user
     * @param postalCode the postal code of the user
     */
    public final void execute(String username, String postalCode) {
        final LoginInputData loginInputData = new LoginInputData(username, postalCode);

        loginInteractor.execute(loginInputData);
    }

    /**
     * Finds nearby locations given a postal code.
     */
    public final void switchToRecommendationView() {
        loginInteractor.switchToRecommendationView();
    }
}

