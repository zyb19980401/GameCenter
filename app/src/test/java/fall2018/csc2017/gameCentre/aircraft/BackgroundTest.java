package fall2018.csc2017.gameCentre.aircraft;

import org.junit.Test;

public class BackgroundTest {

    @Test
    public void run() {
        Background temp = new Background();
    }

    @Test
    public void runElse() {
        Background temp = new Background();
        temp.setX(100);
        temp.setY(100);
        temp.run();

    }

}