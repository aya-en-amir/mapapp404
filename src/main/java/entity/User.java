package entity;

/**
 * A class of User
 */

public class User {
    private String userName;
    private String postalCode;
    private List<UserSession> sessions;

    public User(String userName, String postalCode, List<UserSession> sessions) {
        this.userName = userName;
        this.postalCode = postalCode;
        this.sessions = sessions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<UserSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<UserSession> sessions) {
        this.sessions = sessions;
    }
}