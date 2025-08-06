package use_case.login.login;

/*
Class implementing how login data would be stored
i.e creates a new User with email, name, password and postal code and starts a new UserSession
 */

public class LoginInputData {
    private String username;
    private String postalCode;

    public LoginInputData(String username, String postalCode) {
        this.username = username;
        this.postalCode = postalCode;
    }

    public String getUsername(){
        return username;
    }
    public String getPostalCode(){
        return postalCode;
    }
}
