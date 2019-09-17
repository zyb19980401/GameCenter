package fall2018.csc2017.gameCentre.two_zero_four_eight;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwoBoardManagerTest {
    private TwoBoardManager board1 = new TwoBoardManager();
    private TwoBoardManager board2 = new TwoBoardManager();
    private TwoBoardManager board3 = new TwoBoardManager();
    private TwoBoard test = new TwoBoard();
    private TwoBoardManagerController controller1;
    private TwoBoardManagerController controller2 = new TwoBoardManagerController(board2);
    /**
     * Set a board for test.
     */
    private void setBoard(TwoBoardManager board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j+=2) {
                board.getTwoBoard().setTile(i, j, new TwoTile(2));
            }
        }
    }
    @Before
    public void setupTest(){
        setBoard(board1);
        controller1 = new TwoBoardManagerController(board1);

    }

    @Test
    public void addNewNum() {
        board1.addNewNum();
        controller1.scrollUp(board1.getTwoBoard());
        controller1.scrollLeft(board1.getTwoBoard());
        board1.addNewNum();
        TwoTile[][] temp = board1.getTwoBoard().getTwoTiles();
        boolean result = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j< 4; j++){
                if (temp[i][j].getId() == 4 || temp[i][j].getId() == 2){
                    result = true;
                }
            }
        }
        assertTrue(result);
    }

    @Test
    public void updateScore() {
        setBoard(board2);
        controller2.scrollLeft(board2.getTwoBoard());
        controller2.scrollUp(board2.getTwoBoard());
        controller2.scrollUp(board2.getTwoBoard());
        board2.updateScore();
        assertEquals(24,board2.getCurrentScore());
    }

    @Test
    public void recordBoard() {
        board1.addNewNum();
        board1.addNewNum();
        assertEquals(2,board1.getRecentTwoBoards().size());
    }

    @Test
    public void isValidMove() {
        setBoard(board2);
        assertTrue(board2.isValidMove());
        board2.getTwoBoard().setTile(0,0, new TwoTile(2));
        controller2.scrollRight(board2.getTwoBoard());
        controller2.scrollDown(board2.getTwoBoard());
        assertTrue(board2.isValidMove());
        controller2.scrollDown(board2.getTwoBoard());
        controller2.scrollDown(board2.getTwoBoard());
        assertFalse(board2.isValidMove());

    }

    @Test
    public void undo() {
        board1.getScoreList().add(2);
        board1.addNewNum();
        board1.addNewNum();
        board1.undo();
        assertEquals(1,board1.getRecentTwoBoards().size());
    }

    @Test
    public void checkGameOver() {
        setBoard(board1);
        board1.checkGameOver();
        assertFalse(board1.isGameOver());
        int acc = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board2.getTwoBoard().setTile(i, j, new TwoTile(acc++));
            }
        }
        board2.checkGameOver();
        assertTrue(board2.isGameOver());
    }

    @Test
    public void getRecentTwoBoards() {
        List<TwoBoard> check = new ArrayList<>();
        assertEquals(check, board1.getRecentTwoBoards());
    }

    @Test
    public void getScoreList() {
        controller1.scrollUp(board1.getTwoBoard());
        assertEquals(1,board1.getScoreList().size());
        controller1.scrollUp(board1.getTwoBoard());
        assertEquals(2,board1.getScoreList().size());
    }

    @Test
    public void getCurrentScore() {
        controller1.scrollUp(board1.getTwoBoard());
        board1.updateScore();
        assertEquals(8,board1.getCurrentScore());
        controller1.scrollUp(board1.getTwoBoard());
        board1.updateScore();
        assertEquals(16,board1.getCurrentScore());
    }

    @Test
    public void hasRestarted() {assertFalse(board1.hasRestarted()); }

    @Test
    public void setRestart() {
        board1.setRestart(true);
        assertTrue(board1.hasRestarted());
    }

    @Test
    public void hasUsedUndo() {
        assertFalse(board1.hasUsedUndo());
    }

    @Test
    public void setUsedUndo() {
        board1.setUsedUndo(true);
        assertTrue(board1.hasUsedUndo());
    }

    @Test
    public void getOverCount() {
        setBoard(board1);
        assertFalse(board1.isGameOver());
        board2.addNewNum();
        int acc = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board2.getTwoBoard().setTile(i, j, new TwoTile(acc++));
            }
        }
        board2.checkGameOver();
        assertEquals(1,board2.getOverCount());
    }

    @Test
    public void resetStepsAfterUndo() {
        board1.resetStepsAfterUndo();
        assertEquals(0, board1.getStepsAfterUndo());
    }

    @Test
    public void addStepsAfterUndo() {
        board1.addStepsAfterUndo();
        assertEquals(1, board1.getStepsAfterUndo());
    }


    @Test
    public void getTwoBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j< 4; j++){
                test.setTile(i,j, new TwoTile(0));
            }
        }
        assertTrue(test.equals(board3.getTwoBoard()));
    }
}