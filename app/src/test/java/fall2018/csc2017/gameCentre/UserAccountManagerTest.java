package fall2018.csc2017.gameCentre;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserAccountManagerTest {

    @Test
    public void contains() {
        LoginActivity loginActivity = mock(LoginActivity.class);
        UserAccountManager userAccountManager = UserAccountManager.getUserAccountManager(loginActivity);
        userAccountManager.setMap(new HashMap<>());
        assertFalse(userAccountManager.contains("Nick"));
    }

    @Test
    public void checkMatchness() {
        LoginActivity loginActivity = mock(LoginActivity.class);
        UserAccountManager userAccountManager = UserAccountManager.getUserAccountManager(loginActivity);
        userAccountManager.setMap(new HashMap<>());
        userAccountManager.getMap().put("Nick", new UserAccount("Nick","aaaa"));
        assertFalse(userAccountManager.CheckMatchness("Nick","ssss"));
    }


    @Test
    public void checkMatchnesstrue() {
        LoginActivity loginActivity = mock(LoginActivity.class);
        UserAccountManager userAccountManager = UserAccountManager.getUserAccountManager(loginActivity);
        userAccountManager.setMap(new HashMap<>());
        userAccountManager.getMap().put("Nick", new UserAccount("Nick","aaaa"));
        assertTrue(userAccountManager.CheckMatchness("Nick","aaaa"));
    }
}