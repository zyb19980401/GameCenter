package fall2018.csc2017.gameCentre;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * test score tuple
 */
public class ScoreTupleTest {
    /* a few score tuples */
    private ScoreTuple scoreTuple = new ScoreTuple("Luke", 100, "SlidingTiles");
    private ScoreTuple scoreTuple2 = new ScoreTuple("Jack", 0, "2048");
    private ScoreTuple scoreTuple3 = new ScoreTuple(null,80, null);
    private ScoreTuple scoreTuple4 = null;
    @Test

    /*
     * test get name
     */
    public void getUserName() {
        assertEquals("Luke", scoreTuple.getUserName());
        assertNull(scoreTuple3.getUserName());
    }

    @Test(expected = NullPointerException.class)
    public void testTile(){
        scoreTuple4.getScore();
    }

    /**
     * test game type
     */
    @Test
    public void getGameType() {
        assertEquals("SlidingTiles", scoreTuple.getGameType());
        assertNull(scoreTuple3.getGameType());
    }

    /**
     * test get score
     */
    @Test
    public void getScore() {
        assertEquals(100, scoreTuple.getScore());
        assertEquals(0, scoreTuple2.getScore());
    }

    /**
     * test ToString method
     */
    @Test
    public void TestToString() {
        assertEquals("(username:Luke  Score:100  Gametype:SlidingTiles  )" ,scoreTuple.toString());
        assertEquals("(username:null  Score:80  Gametype:null  )" ,scoreTuple3.toString());
    }

    /*
     * test CompareTo method
     */
    @Test
    public void compareTo() {
        assertEquals(1, scoreTuple.compareTo(scoreTuple2));
        assertEquals(-1, scoreTuple3.compareTo(scoreTuple));
        assertEquals(-1, scoreTuple2.compareTo(scoreTuple3));
    }

    @Test(expected = NullPointerException.class )
    public void testNullPointerException() {
        scoreTuple.GreaterThan(scoreTuple4);
    }
}