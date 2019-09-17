package fall2018.csc2017.gameCentre;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The session test
 */
public class SessionTest {

    /**
     * set up not null session
     */
    private void Setup(){
        Session.login("Luke");
    }

    /**
     * set up the null session
     */
    private void Set(){
        Session.login(null);
    }

    /**
     * test getSession
     */
    @Test
    public void getSession(){
        Set();
        assertNotNull(Session.getSession());
        Setup();
        assertNotNull(Session.getSession());
        Session.logout();
    }

    /**
     * test of get SlidingTile SaveFile
     */
    @Test
    public void getSTSaveFile() {
        Setup();
        assertEquals("LukeSlidingTiles.ser", Session.getSession().getSTSaveFile());
        Session.logout();
        Set();
        assertEquals("nullSlidingTiles.ser", Session.getSession().getSTSaveFile());
        Session.logout();
    }

    /**
     * test of get 2048 saved file
     */
    @Test
    public void getTwoSaveFile() {
        Setup();
        assertEquals("Luke2048.ser", Session.getSession().getTwoSaveFile());
        Session.logout();
        Set();
        assertEquals("null2048.ser", Session.getSession().getTwoSaveFile());
        Session.logout();


    }

    /**
     * test getName
     */
    @Test
    public void getName() {
        Setup();
        assertEquals("Luke", Session.getSession().getName());
        Session.logout();
    }

    /**
     * test login
     */
    @Test
    public void login() {
        Setup();
        assertNotNull(Session.getSession());
        Session.logout();

    }

    /**
     * test log out method
     */
    @Test
    public void logout() {
        Setup();
        Session.logout();
        assertNull(Session.getSession());

    }
}