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
        User newUser = new User(username, postalCode);
        userDataAccessObject.setCurrentUsername(username);
        userDataAccessObject.saveUser(newUser);
        final User user = userDataAccessObject.getUser(loginInputData.getUsername());
        final LoginOutputData loginOutputData = new LoginOutputData(user.getUserName(),
                false);
        loginPresenter.loadSuccessView(loginOutputData);
    }

    @Override
    public void switchToRecommendationView () {
        loginPresenter.switchToRecommendationView();
    }
}
