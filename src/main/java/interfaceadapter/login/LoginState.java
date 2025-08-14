package interfaceadapter.login;

public class LoginState {
    private String username = "";
    private String prompt = "";
    private String postalCode = "";
    private String loginError;

    public final String getUsername() {
        return username;
    }

    public final String getLoginError() {
        return loginError;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public final String getPostalCode() {
        return postalCode;
    }
}

