package use_case.login;

/*
Class implementing how login data would be stored
i.e creates a new User with email, name, password and postal code and starts a new UserSession
 */

import entity.User;
import entity.UserSession;

import java.util.ArrayList;
import java.util.List;

public class LoginInputData {
    private User user;
    private UserSession userSession;

    public LoginInputData(String username, String postalCode) {
        this.user = new User(username, postalCode);
        this.userSession = new UserSession(user);
        ArrayList<UserSession> userSessionList = new ArrayList<>();
        this.user.setSessions(userSessionList);
    }

    public String getUsername(){
        return user.getUserName();
    }
    public String getPostalCode(){
        return user.getPostalCode();
    }
    /*
    should we have this?
     */
    public void addSession(UserSession userSession){
        user.addSession(userSession);
    }
}

