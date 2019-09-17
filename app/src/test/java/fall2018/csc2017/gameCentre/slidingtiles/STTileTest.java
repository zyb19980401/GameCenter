package fall2018.csc2017.gameCentre.slidingtiles;

import org.junit.Test;



import static org.junit.Assert.*;

/**
 * test Tile
 */
public class STTileTest {
    // a few tiles
    private STTile tile1 = new STTile(5,5);
    private STTile tile2 = new STTile(7,3);
    private STTile tile3 = new STTile(5,4);


    /**
     * test get background
     */
    @Test
    public void getBackground() {
        assertNotEquals(null,tile1.getBackground());
        assertNotEquals(null,tile2.getBackground());
    }

    /**
     * test get id
     */
    @Test
    public void getId() {
        assertEquals(8,tile2.getId());
        assertEquals(6,tile3.getId());
    }

    /**
     * test compare two tiles
     */
    @Test
    public void compareTo() {
        STTile newTile = new STTile(5,4);
        assertSame(newTile.getId(), this.tile3.getId());
        assertEquals(2,tile1.compareTo(tile2));
    }

}