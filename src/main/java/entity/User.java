package entity;

/**
 * A class representing a user.
 */
public class User {
    private String userName;
    private String postalCode;

    public User(String userName, String postalCode) {
        this.userName = userName;
        this.postalCode = postalCode;
    }

    public final String getUserName() {
        return userName;
    }

    public final String getPostalCode() {
        return postalCode;
    }

    public final void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
