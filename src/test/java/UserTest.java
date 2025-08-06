

import entity.User;
import entity.UserSession;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserTest {

    @Test
    public void testConstructorAndGetters() {
        User user = new User("Enaya", "M5S2E4");

        assertEquals("Enaya", user.getUserName());
        assertEquals("M5S2E4", user.getPostalCode());
        assertNotNull(user.getSessions());
        assertTrue(user.getSessions().isEmpty());
    }

    @Test
    public void testSetters() {
        User user = new User("Ira", "M5S2E4");

        user.setUserName("Isha");
        user.setPostalCode("M5S2E5");

        assertEquals("Isha", user.getUserName());
        assertEquals("M5S2E5", user.getPostalCode());
    }

    @Test
    public void testAddSingleSession() {
        User user = new User("Olivia", "M5S2E4");
        UserSession session = new UserSession(user);

        user.addSession(session);

        assertEquals(1, user.getSessions().size());
    }

    @Test
    public void testAddMultipleSessions() {
        User user = new User("Haeun", "M5S2E4");
        UserSession session1 = new UserSession(user);
        UserSession session2 = new UserSession(user);

        user.addSessions(Arrays.asList(session1, session2));

        assertEquals(2, user.getSessions().size());
    }

    @Test
    public void testSetSessionsDirectly() {
        User user = new User("Jessie", "M5S2E4");
        UserSession session1 = new UserSession(user);
        UserSession session2 = new UserSession(user);

        List<UserSession> newSessions = Arrays.asList(session1, session2);
        user.setSessions(newSessions);

        assertEquals(2, user.getSessions().size());
    }

    @Test
    public void testEmptySessions() {
        User user = new User("Pan", "M5S2E4");

        user.addSessions(Collections.emptyList());

        assertTrue(user.getSessions().isEmpty());
    }

    @Test
    public void testNullSessionsInSetter() {
        User user = new User("Jonathan", "M5S2E4");
        user.setSessions(null);

        assertNull(user.getSessions());
    }
}
