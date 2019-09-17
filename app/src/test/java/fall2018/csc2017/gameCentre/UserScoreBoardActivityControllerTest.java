package fall2018.csc2017.gameCentre;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;



public class UserScoreBoardActivityControllerTest {

    @Test
    public void helperTempIsNull() {
        UserScoreBoardActivityController temp = new UserScoreBoardActivityController("Tile",null);
        assertNull(temp.helper());
    }

    /**
     * the test for temp is empty
     */
    @Test
    @SuppressWarnings("unchecked")
    public void helperTempIsEmpty(){

        ArrayList acc = new ArrayList();
        UserScoreBoardActivityController temp = new UserScoreBoardActivityController("Tile",acc);
        assertNotNull(temp.helper());
        assertTrue(temp.helper().isEmpty());
    }

    /**
     * the temt for temp contains one Score_tuple.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void helperTempIsNotEmpty(){
        ArrayList acc = new ArrayList();
        acc.add (new ScoreTuple("Nick", 123, "Tiles"));
        UserScoreBoardActivityController temp = new UserScoreBoardActivityController("Tiles",acc);
        assertNotNull(temp.helper());
        assertEquals(temp.helper().get(0),acc.get(0));
    }


}