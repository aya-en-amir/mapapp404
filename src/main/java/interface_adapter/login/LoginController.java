package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginInputBoundary;
    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }
    public void execute(String username, String postalCode) {
        final LoginInputData loginInputData = new LoginInputData(
                username, postalCode);

        loginInputBoundary.execute(loginInputData);
    }
}
