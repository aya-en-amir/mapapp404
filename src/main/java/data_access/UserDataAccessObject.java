package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
import interface_service.UserDataAccessInterface;

public class UserDataAccessObject implements UserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();
    private String currentUsername;

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void saveUser(User user) {
        users.put(user.getUserName(), user);
    }

    @Override
    public void deleteUser(String username) {
        users.remove(username);
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

}
