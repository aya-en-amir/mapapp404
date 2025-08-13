package use_case.login;

/**
Class implementing how login data would be stored
i.e creates a new User with email, name, password and postal code and starts a new UserSession
 */
import entity.User;

public class LoginInputData {
    private User user;

    public LoginInputData(String username, String postalCode) {
        this.user = new User(username, postalCode);
    }

    public String getUsername(){
        return user.getUserName();
    }
    public String getPostalCode(){
        return user.getPostalCode();
    }
}

