package fall2018.csc2017.gameCentre;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTileTest {
    private GameTile gameTile1 = new GameTile();

    @Test
    public void setId() {
        gameTile1.setId(1);
        assertEquals(1,gameTile1.getId());
    }
}