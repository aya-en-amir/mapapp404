package use_case.login;

import entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String postalCode = loginInputData.getPostalCode();
        if (postalCode.charAt(0) != 'M') {
            loginPresenter.loadFailView(postalCode + ": Invalid postal code. Postal code must " +
                    "start with 'M'");
        }

        if (postalCode.length() !=7) {
            loginPresenter.loadFailView(postalCode + ": Invalid postal code. Postal code must be in " +
                    "format of MXX XXX'");
        }

        if (!userDataAccessObject.existsByName(username)) {
            User newUser = new User(username, postalCode);
            userDataAccessObject.saveUser(newUser);
        }
        userDataAccessObject.setCurrentUsername(username);
        final User user = userDataAccessObject.getUser(loginInputData.getUsername());
        final LoginOutputData loginOutputData = new LoginOutputData(user.getUserName(), user.getPostalCode(),
                false);
        loginPresenter.loadSuccessView(loginOutputData);
    }
}
