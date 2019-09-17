package fall2018.csc2017.gameCentre.aircraft;

import org.junit.Test;

import static fall2018.csc2017.gameCentre.aircraft.ACGameActivityController.getSkyManager;
import static org.junit.Assert.*;

public class MyAircraftTest {

    @Test
    public void addNumKilled() {
        MyAircraft aircraft = new MyAircraft();
        aircraft.addNumKilled();
        assertEquals(1, aircraft.numKilled);
    }

    @Test
    public void hitByBullet() {
        MyAircraft aircraft = new MyAircraft();
        EnemyAircraft enemyAircraft = new EnemyAircraft();
        Bullet bullet = new Bullet(enemyAircraft);
        getSkyManager().addEnemyBulletsList(bullet);
        aircraft.isHitBy(bullet);
    }

    @Test
    public void hp0() {
        MyAircraft aircraft = new MyAircraft();
        EnemyAircraft enemyAircraft = new EnemyAircraft();
        Bullet bullet = new Bullet(enemyAircraft);
        aircraft.setHp(0);
        getSkyManager().addEnemyBulletsList(bullet);
        aircraft.isHitBy(bullet);
    }

    @Test
    public void run() {
    }
}