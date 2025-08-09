package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }
    public void execute(String username, String postalCode) {
        final LoginInputData loginInputData = new LoginInputData(username, postalCode);

        loginInteractor.execute(loginInputData);
    }

    public void switchToRecommendationView() {
        loginInteractor.switchToRecommendationView();
    }
}

