import data_access.UserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDataAccessObjectTest {

    private UserDataAccessObject userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new UserDataAccessObject();
    }

    @Test
    void testSaveAndGetUser() {
        User user = new User("enaya", "M5S 2E4");
        userDAO.saveUser(user);

        User retrieved = userDAO.getUser("enaya");

        assertNotNull(retrieved);
        assertEquals("enaya", retrieved.getUserName());
    }

    @Test
    void testGetNonExistentUserReturnsNull() {
        User result = userDAO.getUser("nonexistent");
        assertNull(result);
    }

    @Test
    void testDeleteUser() {
        User user = new User("ira", "M5S 2E4");
        userDAO.saveUser(user);

        assertNotNull(userDAO.getUser("ira"));

        userDAO.deleteUser("ira");

        assertNull(userDAO.getUser("ira"));
    }

    @Test
    void testSetAndGetCurrentUsername() {
        userDAO.setCurrentUsername("olivia");
        assertEquals("olivia", userDAO.getCurrentUsername());
    }

    @Test
    void testOverwriteUserWithSameUsername() {
        User user1 = new User("isha", "M5S 2E4");
        User user2 = new User("isha", "M5S 2E5"); // Same username, possibly different instance

        userDAO.saveUser(user1);
        userDAO.saveUser(user2); // Should overwrite the first one

        User retrieved = userDAO.getUser("isha");

        assertEquals("isha", retrieved.getUserName());
        assertSame(user2, retrieved); // Should be the second instance
    }

    @Test
    void testDeleteNonexistentUserDoesNotThrow() {
        assertDoesNotThrow(() -> userDAO.deleteUser("haeun"));
    }

    @Test
    void testCurrentUsernameInitiallyNull() {
        assertNull(userDAO.getCurrentUsername());
    }
}
