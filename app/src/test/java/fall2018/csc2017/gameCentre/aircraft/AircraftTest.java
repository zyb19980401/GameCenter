package fall2018.csc2017.gameCentre.aircraft;

import org.junit.Test;

import static fall2018.csc2017.gameCentre.aircraft.ACGameActivityController.getSkyManager;
import static org.junit.Assert.*;

public class AircraftTest {
    private Aircraft aircraft = new MyAircraft();

    @Test
    public void getHp() {
        aircraft.setHp(5);
        int hp = aircraft.getHp();
        assertEquals(5, hp);
    }

    @Test
    public void setHp() {
        Aircraft aircraft = new MyAircraft();
        aircraft.setHp(3);
        int hp = aircraft.getHp();
        assertEquals(3, hp);
    }

    @Test
    public void setEnemyHp() {
        Aircraft aircraft = new EnemyAircraft();
        aircraft.setHp(3);
        int hp = aircraft.getHp();
        assertEquals(3, hp);
    }

    @Test
    public void isHitBy() {
        Aircraft enemy = new EnemyAircraft();
        Bullet bullet = new Bullet(enemy);
        assertTrue(aircraft.isHitBy(bullet));

    }

    @Test
    public void checkLeftHit() {
        Aircraft enemy = new EnemyAircraft();
        Bullet bullet = new Bullet(enemy);
        float px = 25 * getSkyManager().getRate();
        Boolean check = aircraft.checkLeftHit(px, aircraft, bullet);
        assertTrue(check);
    }

    @Test
    public void checkTopHit() {
        Aircraft enemy = new EnemyAircraft();
        Bullet bullet = new Bullet(enemy);
        float px = 30 * getSkyManager().getRate();
        Boolean check = aircraft.checkTopHit(px, aircraft, bullet);
        assertTrue(check);
    }

    @Test
    public void run() {
    }
}