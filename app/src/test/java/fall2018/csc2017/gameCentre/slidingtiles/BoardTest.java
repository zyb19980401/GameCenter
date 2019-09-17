package fall2018.csc2017.gameCentre.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * test Board
 */
public class BoardTest {

    /** The board for testing. */
    private Board board;

    /**
     * Set board.
     * @param complexity the complexity
     */
    private void SetBoard(int complexity){
        List<STTile> tiles = new ArrayList<>();
        final int numTiles = complexity * complexity;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new STTile(tileNum, complexity));
        }
        this.board = new Board(tiles, complexity);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testComplexity(){
        SetBoard(6);
    }

    /**
     * test getNumSteps
     */
    @Test
    public void getNumStep() {
        SetBoard(3);
        assertEquals(0, board.getNumStep());
        board.swapTiles(0,0,0,1);
        assertEquals(1, board.getNumStep());
        // see if NumStep not change after the invalid move
        board.swapTiles(0,1,0,2);
        assertEquals(2, board.getNumStep());
    }

    /**
     * test get and set Complexity.
     */
    @Test
    public void Complexity() {
        SetBoard(3);
        assertEquals(3, board.getComplexity());
        board.setComplexity(4);
        assertEquals(4, board.getComplexity());
    }

    @Test(expected = NullPointerException.class)
    public void testSetComplexity(){
        board.setComplexity(7);

    }

    @Test(expected = NullPointerException.class)
    public void testsetComplexity(){
        board.setComplexity(1);
    }

    /**
     * test Iterator
     */
    @Test
    public void Iterator() {
        SetBoard(3);
        Iterator<STTile> a = board.iterator();
        assertTrue(a.hasNext());
        assertEquals(board.getSTTiles()[0][0],a.next());
    }

    /**
     * test get numTile.
     */
    @Test
    public void numTiles() {
        SetBoard(3);
        assertEquals(9, board.numTiles());
    }

    /**
     * test resetNumStep.
     */
    @Test
    public void resetNumStep(){
        SetBoard(3);
        board.resetNumStep();
        assertEquals(0, board.getNumStep());
    }


}