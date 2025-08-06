package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final String postalcode;
    private final boolean useCaseFailed;

    public LoginOutputData(String username, String postalcode, boolean useCaseFailed) {
        this.username = username;
        this.postalcode = postalcode;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {

        return username;
    }

    public String getPostalcode() {
        return postalcode;
    }
}
