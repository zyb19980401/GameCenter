
package fall2018.csc2017.gameCentre.aircraft;

import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ACGameOverActivityControllerTest {
        @InjectMocks
        private ACStartingActivity acstartingActivity = mock(ACStartingActivity.class);
        private ACGameOverActivityController controller = new ACGameOverActivityController(acstartingActivity);
        private SkyManager skyManager = new SkyManager();

        @Test
        public void setSkyManager() {
            ACGameOverActivityController.setSkyManager(skyManager);
            assertEquals(ACGameOverActivityController.getSkyManager(), skyManager);
        }

        @Test
        public void killString() {
            MyAircraft aircraft = new MyAircraft();
            skyManager.setMyAircraft(aircraft);
            ACGameOverActivityController.setSkyManager(skyManager);
            assertEquals("You have killed  0  enemy", controller.killString());

        }

        @Test
        public void timeString() {
            MyAircraft aircraft = new MyAircraft();
            skyManager.setMyAircraft(aircraft);
            ACGameOverActivityController.setSkyManager(skyManager);
            assertEquals("You have survived  00:00", controller.timeString());
        }

        @Test
        public void scoreString() {
            MyAircraft aircraft = new MyAircraft();
            skyManager.setMyAircraft(aircraft);
            ACGameOverActivityController.setSkyManager(skyManager);
            assertEquals("You have got 0 points !!!!", controller.scoreString());
        }

        @Test
        public void setScoreTuple() {
            MyAircraft aircraft = new MyAircraft();
            skyManager.setMyAircraft(aircraft);
            try{controller.setScoreTuple("Nick");
            }
            catch (NullPointerException e){
                System.out.println(e);
            }

        }
    }

