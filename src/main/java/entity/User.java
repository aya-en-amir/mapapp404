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

    public String getUserName() {
        return userName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}