package fall2018.csc2017.gameCentre.two_zero_four_eight;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoTileTest {
    private TwoTile TwoTile1 = new TwoTile(5,5);
    private TwoTile TwoTile2 = new TwoTile(7,3);
    private TwoTile TwoTile4 = new TwoTile();
    private TwoTile TwoTile5 = new TwoTile(1);

    @Test
    public void equals() {
        assertFalse(TwoTile1.equals(TwoTile5));
        assertTrue(TwoTile1.equals(TwoTile4));
    }


    @Test
    public void getX() {assertEquals(5, TwoTile1.getX());
    }

    @Test
    public void getY() {assertEquals(3, TwoTile2.getY());
    }
}