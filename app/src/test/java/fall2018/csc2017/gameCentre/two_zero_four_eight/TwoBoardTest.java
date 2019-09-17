package fall2018.csc2017.gameCentre.two_zero_four_eight;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoBoardTest {
    private TwoTile[][] twoTiles;
    private TwoBoard TwoBoard1 = new TwoBoard();
    private TwoBoard TwoBoard2 = new TwoBoard();
    private TwoBoard TwoBoard3 = new TwoBoard();
    private TwoBoard TwoBoard4 = new TwoBoard();
    private TwoTile test = new TwoTile(1);

    @Test
    public void setTile() {
        TwoBoard1.setTile(2,2,test);
        twoTiles = TwoBoard1.getTwoTiles();
        assertEquals(twoTiles[2][2],TwoBoard1.getTile(2,2));
    }

    @Test
    public void getTile() {
        TwoBoard1.setTile(1,1,test);
        twoTiles = TwoBoard1.getTwoTiles();
        assertEquals(twoTiles[1][1],TwoBoard1.getTile(1,1));
    }


    @Test
    public void equals() {
        TwoBoard1.setTile(1,1,test);
        TwoBoard2.setTile(1,2, test);
        assertFalse(TwoBoard1.equals(TwoBoard2));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j< 4; j++){
                TwoBoard3.setTile(i,j, new TwoTile(0));
                TwoBoard4.setTile(i,j, new TwoTile(0));
            }
        }
        assertTrue(TwoBoard3.equals(TwoBoard4));

    }
}