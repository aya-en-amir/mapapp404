package entity;

import java.util.List;
import java.util.ArrayList;

/**
 * A class representing a user.
 */
public class User {
    private String userName;
    private String postalCode;
    private List<UserSession> sessions;

    public User(String userName, String postalCode) {
        this.userName = userName;
        this.postalCode = postalCode;
        this.sessions = new ArrayList<>();
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

    public void addSession(UserSession session) {
        this.sessions.add(session);
    }

    public void addSessions(List<UserSession> sessions) {
        for (UserSession userSession : sessions) {
            this.sessions.add(userSession);
        }
    }
}