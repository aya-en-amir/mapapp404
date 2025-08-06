package interface_adapter.LoggedIn;

public class LoggedInState {
    private String username = "";
    private String postalCode = "";
    private String postalError;

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        postalCode = copy.postalCode;
        postalError = copy.postalError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalError(String postalError) {
        this.postalError = postalError;
    }
}
