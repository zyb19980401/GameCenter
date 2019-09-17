package fall2018.csc2017.gameCentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserAccountTest {

    private UserAccount UserAccount1 = new UserAccount("Luke", "abc123");
    private UserAccount UserAccount2 = new UserAccount("$", " ");
    @Test
    public void getName() {
        assertEquals("Luke", UserAccount1.getName());
        assertNotEquals("Jack", UserAccount1.getName());
        assertNotEquals("", UserAccount1.getName());
        assertNotEquals(" $", UserAccount2.getName());
    }

    @Test
    public void getPassword() {
        assertNotEquals("abc1234",UserAccount1.getPassword());
        assertNotEquals("",UserAccount1.getPassword());
        assertEquals("abc123", UserAccount1.getPassword());
        assertNotEquals("", UserAccount2.getPassword());
        assertNotEquals("  ", UserAccount2.getPassword());
    }
}