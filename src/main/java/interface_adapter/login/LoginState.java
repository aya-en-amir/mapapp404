package interface_adapter.login;

/*
State of the user login
 */

import entity.User;

public class LoginState {
    private User user;
    private String loginError;
    private String userSessionID;

    public String getUsername(){
        return user.getUserName();
    }
    public String getPostalCode(){
        return user.getPostalCode();
    }
    public void setUsername(String username){
        this.user.setUserName(username);
    }
    public void setPostalCode(String postalCode){
        this.user.setPostalCode(postalCode);
    }
    public void setLoginError(String loginError){
        this.loginError = loginError;
    }
    public String getLoginError(){
        return loginError;
    }

}

