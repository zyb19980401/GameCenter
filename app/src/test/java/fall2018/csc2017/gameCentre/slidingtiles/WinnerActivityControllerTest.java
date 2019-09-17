package fall2018.csc2017.gameCentre.slidingtiles;

import org.junit.Test;

import org.junit.Before;
import org.mockito.InjectMocks;

import fall2018.csc2017.gameCentre.ScoreTuple;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class WinnerActivityControllerTest {
    @InjectMocks
    private WinnerActivityController controller;


    @Before
    public void setupTests() {
        WinnerActivity winnerActivity = mock(WinnerActivity.class);
        controller = new WinnerActivityController(winnerActivity);

    }

    @Test
    public void get_string() {
        String score = controller.get_string(111);
        assertEquals(score, "Your score is: 111");
    }


    @Test
    public void add_score_exception(){
        ScoreTuple score = new ScoreTuple("Nick",111,"Tiles");
        try{controller.addScore("Tile",score);
        }
        catch (NullPointerException e){
            System.out.println(e);
        }
    }


    @Test
    public void top_score_exception(){
        ScoreTuple score = new ScoreTuple("Nick",111,"Tiles");
        try{controller.addTop("Tile",score);
        }
        catch (NullPointerException e){
            System.out.println(e);
        }
    }
}