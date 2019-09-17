package fall2018.csc2017.gameCentre.slidingtiles;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;


import static org.junit.Assert.*;

public class GameActivityControllerTest {
    @InjectMocks
    private GameActivityController controller;

    @Before
    public void setupTest(){
        BoardManager boardManager = new BoardManager(4);
        controller = new GameActivityController(boardManager);
    }


    @Test
    public void getStep() {
        controller.update(controller.getBoardManager().getBoard(),1);
        assertNotNull(controller.getStep());
    }

    @Test
    public void getTimeLeftFormatted(){
        controller.generateTimeLeftFormatted();
        assertNotNull(controller.getTimeLeftFormatted());
    }
    @Test
    public void checkWin() {
        assertFalse(controller.checkWin());
    }


    @Test

    public void generateTimeLeftFormatted(){
        String tmp = controller.getTimeLeftFormatted();
        controller.generateTimeLeftFormatted();
        assertNotEquals(tmp,controller.getTimeLeftFormatted());
    }



    @Test
    public void getBoard(){
        assertNotNull(controller.getBoard());
    }

    @Test
    public void update(){
        controller.update(controller.getBoardManager().getBoard(),1);
        assertEquals("step: 1",controller.getStep());
    }

    @Test
    public void getComplexity(){
        assertEquals(4,controller.getComplexity());
    }


}
