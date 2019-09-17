package fall2018.csc2017.gameCentre.slidingtiles;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

/**
 * test BoardManager
 */
public class BoardManagerTest {

    /** The board manager for testing. */
    private BoardManager boardManager;
    private BoardManager boardManager2;

    /**
     * Make a set of tiles that are in order.
     * @return a set of tiles that are in order
     */
    private List<STTile> makeTiles() {
        List<STTile> tiles = new ArrayList<>();
        final int numTiles = 16;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new STTile(tileNum, 4));
        }

        return tiles;
    }

    /**
     * Make a solved Board. randomly assign attributes
     */
    private void setUpCorrect() {
        List<STTile> tiles = makeTiles();
        Board board = new Board(tiles, 4);
        boardManager = new BoardManager(board);
        boardManager.SetMTimeLeftInMillis(3000);
        boardManager.setNumUndo(4);
        boardManager.getBoard().resetNumStep();
    }

    /**
     * Make another randomly shuffled Board.
     */
    private void SetUp(){
        boardManager2 = new BoardManager(3);
    }
    /**
     * swap two tiles.
     */
    private void swapFirstTwoTiles() {
        boardManager.getBoard().swapTiles(0, 0, 0, 1);
    }

    /**
     * Set time to a specific int.
     */
    private void SetTime(int time){
        boardManager.SetMTimeLeftInMillis(time);
    }

    /**
     *  test get time left.
     */
    @Test
    public void getMTimeLeftInMillis() {
        setUpCorrect();
        assertEquals(3000, boardManager.getMTimeLeftInMillis());
        SetTime(1000);
        assertEquals(1000, boardManager.getMTimeLeftInMillis());
        SetTime(0);
        assertEquals(0, boardManager.getMTimeLeftInMillis());
        SetUp();
        assertEquals(0, boardManager.getMTimeLeftInMillis());
        SetTime(99999);
        assertEquals(99999, boardManager.getMTimeLeftInMillis());
    }

    /**
     *  test addTime
     */
    @Test
    public void addTime() {
        setUpCorrect();
        assertEquals(3000, boardManager.getMTimeLeftInMillis());
        boardManager.addTime();
        assertEquals(4000, boardManager.getMTimeLeftInMillis());
        SetUp();
        boardManager2.addTime();
        assertEquals(1000, boardManager2.getMTimeLeftInMillis());
    }

    /**
     *  test getBoard
     */
    @Test
    public void getBoard() {
        List<STTile> tiles = makeTiles();
        Board board = new Board(tiles, 4);
        boardManager = new BoardManager(board);
        assertSame(board,boardManager.getBoard());

    }

    /**
     * Test whether swapping two tiles makes a solved board unsolved.
     */
    @Test
    public void puzzleSolved() {
        setUpCorrect();
        assertTrue(boardManager.puzzleSolved());
        swapFirstTwoTiles();
        assertFalse(boardManager.puzzleSolved());
        SetUp();
        assertFalse(boardManager.puzzleSolved());
    }

    /**
     * Test whether tap is valid
     */
    @Test
    public void isValidTap() {
        setUpCorrect();
        assertTrue(boardManager.isValidTap(11));
        assertTrue(boardManager.isValidTap(14));
        assertFalse(boardManager.isValidTap(10));
    }

    @Test(expected = NullPointerException.class)
    public void testSetComplexity(){
        SetUp();
        boardManager.isValidTap(17);
    }

    /**
     * Test whether touchMove is ok
     */
    @Test
    public void touchMove() {
        setUpCorrect();
        assertEquals(12, boardManager.getBoard().getTile(2, 3).getId());
        assertEquals(16, boardManager.getBoard().getTile(3, 3).getId());
        boardManager.touchMove(11);
        assertEquals(16, boardManager.getBoard().getTile(2, 3).getId());
        assertEquals(12, boardManager.getBoard().getTile(3, 3).getId());
        boardManager.touchMove(7);
        assertEquals(16, boardManager.getBoard().getTile(1, 3).getId());
        assertEquals(8, boardManager.getBoard().getTile(2, 3).getId());
        // check when apply invalid tap, nothing changes
        boardManager.touchMove(14);
        assertEquals(16, boardManager.getBoard().getTile(1, 3).getId());
        assertEquals(8, boardManager.getBoard().getTile(2, 3).getId());

    }

    /**
     * Test whether recordStep works.
     */
    @Test
    public void testRecordStep(){
        setUpCorrect();
        assertEquals(12, boardManager.getBoard().getTile(2, 3).getId());
        assertEquals(16, boardManager.getBoard().getTile(3, 3).getId());
        boardManager.touchMove(11);
        assertArrayEquals(new int[]{2, 3, 3, 3}, boardManager.recentSteps.get(boardManager.recentSteps.size() - 1));
        boardManager.touchMove(7);
        assertArrayEquals(new int[]{1, 3, 2, 3}, boardManager.recentSteps.get(boardManager.recentSteps.size() - 1));
        boardManager.touchMove(6);
        assertArrayEquals(new int[]{1, 2, 1, 3}, boardManager.recentSteps.get(boardManager.recentSteps.size() - 1));
        boardManager.touchMove(10);
        // check else loop which removes item at index 0 when recentSteps.size() >= numSteps
        assertArrayEquals(new int[]{1, 3, 2, 3}, boardManager.recentSteps.get(0));
    }

    @Test
    public void undo() {
        setUpCorrect();
        boardManager.touchMove(11);
        boardManager.touchMove(10);
        boardManager.touchMove(9);
        boardManager.touchMove(5);
        assertEquals(4, boardManager.getNumUndo());
        boardManager.undo(boardManager.recentSteps);
        assertEquals(16, boardManager.getBoard().getTile(2, 1).getId());
        assertEquals(5, boardManager.getNumUndo());
        boardManager.undo(boardManager.recentSteps);
        assertEquals(16, boardManager.getBoard().getTile(2, 2).getId());
        assertEquals(6, boardManager.getNumUndo());
        boardManager.undo(boardManager.recentSteps);
        assertEquals(16, boardManager.getBoard().getTile(2, 3).getId());
        assertEquals(7, boardManager.getNumUndo());
        boardManager.undo(boardManager.recentSteps);
        //see what happen if excess maximum undo steps
        assertEquals(16, boardManager.getBoard().getTile(2, 3).getId());
        assertEquals(7, boardManager.getNumUndo());
    }

    /**
     * Test whether set and get NumUndo is valid
     */
    @Test
    public void getNumUndo(){
        setUpCorrect();
        boardManager.setNumUndo(0);
        assertEquals(0,boardManager.getNumUndo());
        boardManager.setNumUndo(5);
        assertEquals(5,boardManager.getNumUndo());
    }

    /**
     * Test shuffle.
     */
    @Test
    public void shuffle(){
        SetUp();
        assertFalse(boardManager2.isSolved);
    }

    /**
     * Test Set Board.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void set_board(){
        BoardManager a = new BoardManager(4);
        List<STTile> acc = new ArrayList();
        final int numTiles = 4 * 4;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
                acc.add(new STTile(tileNum, 4));
        }
        Board temp = new Board(acc,4);
        a.setBoard(temp);
        assertEquals(temp, a.getBoard());
    }

    /**
     * Test get score.
     */
    @Test
    public void get_score(){
        BoardManager temp = new BoardManager(4);
        temp.setMTimeLeftInMillis(150000);
        assertEquals(438,temp.get_score());
        temp.setMTimeLeftInMillis(0);
        assertEquals(460,temp.get_score());

    }
}