package fall2018.csc2017.gameCentre.two_zero_four_eight;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class GameActivityControllerTest {
    @InjectMocks
    private fall2018.csc2017.gameCentre.two_zero_four_eight.GameActivityController controller1;
    private fall2018.csc2017.gameCentre.two_zero_four_eight.GameActivityController controller2;
    private fall2018.csc2017.gameCentre.two_zero_four_eight.GameActivityController controller3;
    private fall2018.csc2017.gameCentre.two_zero_four_eight.GameActivityController controller4;
    private TwoBoardManager boardManager1 = new TwoBoardManager();
    private TwoBoardManager boardManager2 = new TwoBoardManager();
    private TwoBoardManager boardManager3 = new TwoBoardManager();
    private TwoBoardManager boardManager4 = new TwoBoardManager();
    private TwoBoardManager check1 = new TwoBoardManager();
    private TwoBoardManager check2 = new TwoBoardManager();
    private TwoBoardManager check3 = new TwoBoardManager();
    private TwoBoardManager check4 = new TwoBoardManager();


    @Before
    public void setupTest(){
        setBoard(boardManager1);
        controller1 = new GameActivityController(boardManager1);
        setBoard(boardManager2);
        controller2 = new GameActivityController(boardManager2);
        setBoard(boardManager3);
        controller3 = new GameActivityController(boardManager3);
        setBoard(boardManager4);
        controller4 = new GameActivityController(boardManager4);

    }

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

    @Test
    public void execution() {
    }

    @Test
    public void startGame() {
    }

    @Test
    public void getBMScore() {
        controller1.action("left");
        controller1.getTwoBoardManager().updateScore();
        assertEquals(8,controller1.getBMScore());
        controller1.action("up");
        controller1.getTwoBoardManager().updateScore();
        assertEquals(16,controller1.getBMScore());
    }

    @Test
    public void enableUndo() {
    }

    @Test
    public void update() {
    }

    @Test
    public void action() {
        controller1.action("left");
        check1.getTwoBoard().setTile(0,0,new TwoTile(4));
        check1.getTwoBoard().setTile(1,0,new TwoTile(4));
        check1.getTwoBoard().setTile(2,0,new TwoTile(4));
        check1.getTwoBoard().setTile(3,0,new TwoTile(4));
        assertTrue(controller1.getTwoBoardManager().getTwoBoard().equals(check1.getTwoBoard()));
        controller2.action("right");
        check2.getTwoBoard().setTile(0,3,new TwoTile(4));
        check2.getTwoBoard().setTile(1,3,new TwoTile(4));
        check2.getTwoBoard().setTile(2,3,new TwoTile(4));
        check2.getTwoBoard().setTile(3,3,new TwoTile(4));
        assertTrue(controller2.getTwoBoardManager().getTwoBoard().equals(check2.getTwoBoard()));
        controller3.action("up");
        check3.getTwoBoard().setTile(0,0,new TwoTile(4));
        check3.getTwoBoard().setTile(1,0,new TwoTile(4));
        check3.getTwoBoard().setTile(0,2,new TwoTile(4));
        check3.getTwoBoard().setTile(1,2,new TwoTile(4));
        assertTrue(controller3.getTwoBoardManager().getTwoBoard().equals(check3.getTwoBoard()));
        controller4.action("down");
        check4.getTwoBoard().setTile(2,0,new TwoTile(4));
        check4.getTwoBoard().setTile(3,0,new TwoTile(4));
        check4.getTwoBoard().setTile(2,2,new TwoTile(4));
        check4.getTwoBoard().setTile(3,2,new TwoTile(4));
        assertTrue(controller4.getTwoBoardManager().getTwoBoard().equals(check4.getTwoBoard()));
    }

    @Test
    public void setBMRestart() {
        controller1.setBMRestart();
        assertTrue(controller1.getTwoBoardManager().hasRestarted());
    }

    @Test
    public void setBMUsedUndo() {
        controller1.setBMUsedUndo();
        assertTrue(controller1.getTwoBoardManager().hasUsedUndo());
    }

    @Test
    public void BMUndo() {
        controller1.getTwoBoardManager().addNewNum();
        controller1.getTwoBoardManager().addNewNum();
        controller1.action("left");
        controller1.BMUndo();
        assertEquals(2,controller1.getTwoBoardManager().getRecentTwoBoards().size());
    }

    @Test
    public void isGameOver() {
        assertFalse(controller2.isGameOver());
    }

    @Test
    public void setNewGame() {
        controller2.setNewGame(false);
        assertFalse(controller2.getNewGame());
    }

}