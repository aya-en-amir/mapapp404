package interface_adapter.login;

public class LoginState {
    private String username = "";
    private String loginError;
    private String postalCode = "";

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
