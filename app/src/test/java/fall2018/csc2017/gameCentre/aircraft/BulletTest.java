package fall2018.csc2017.gameCentre.aircraft;

import org.junit.Test;

import static org.junit.Assert.*;

public class BulletTest {

    @Test
    public void setRunning() {
        Aircraft aircraft = new MyAircraft();
        Bullet bullet = new Bullet(aircraft);
        bullet.setRunning(true);
        bullet.setHeight(11);
        assertEquals(11, bullet.getHeight(), 0.0);
        bullet.setSpeed(10);
        assertEquals(10, bullet.getSpeed(), 0.0);
        assertNotNull(bullet);
    }

    @Test
    public void setEnemyRunning() {
        Aircraft aircraft = new MyAircraft();
        Bullet bullet = new Bullet(aircraft);
        bullet.setRunning(true);
        bullet.setHeight(11);
        assertEquals(11, bullet.getHeight(), 0.0);
        bullet.setSpeed(10);
        assertEquals(10, bullet.getSpeed(), 0.0);
        assertNotNull(bullet);
    }

}