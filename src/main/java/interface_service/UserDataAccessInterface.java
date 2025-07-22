package interface_service;

import entity.User;

public interface UserDataAccessInterface {
    /**
     * Save a specific user.
     * @param user the current user's information
     */
    void saveUser(User user);

    /**
     * Get a specific user from the user's name.
     * @param userName the name of the user we want
     */
    User getUser(String userName);

    /**
     * Delete the user's information from their username.
     * @param userName the name of the user we want
     */
    String deleteUser(String userName);
}
