package use_case.login;

/*
Class implementing how login data would be stored
i.e creates a new User with email, name, password and postal code and starts a new UserSession
 */

public class LoginInputData {
    private String username;

    public LoginInputData(String username) {
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
}
