package use_case.login;

import entity.User;

/**
 * Creates a new User with email, name, password and postal code and starts a new UserSession.
 */
public class LoginInputData {
    private User user;

    public LoginInputData(String username, String postalCode) {
        this.user = new User(username, postalCode);
    }

    public final String getUsername() {
        return user.getUserName();
    }

    public final String getPostalCode() {
        return user.getPostalCode();
    }
}
