package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    /**
     * Save a specific user.
     * @param user the current user's information
     */
    void saveUser(User user);

    /**
     * Get a specific user from the user's name.
     * @param username the name of the user we want
     */
    User getUser(String username);

    /**
     * Delete the user's information from their username.
     * @param username the name of the user we want
     */
    void deleteUser(String username);

    /**
     * Returns the username of the current user.
     */
    String getCurrentUsername();

    /**
     * Setting the current user.
     * @param username the name of the current user
     */
    void setCurrentUsername(String username);

    /**
     * Check if the username exists
     * @param username the name of the current user
     */
    boolean existsByName(String username);

}
