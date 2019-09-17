package fall2018.csc2017.gameCentre.aircraft;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SkyManagerTest {

    private SkyManager skyManager = new SkyManager();

    @Test
    public void getWidth() {
        skyManager.setWidth(4);
        int temp = skyManager.getWidth();
        assertEquals(temp, 4);
    }

    @Test
    public void setWidth() {
        skyManager.setWidth(5);
        int temp = skyManager.getWidth();
        assertEquals(temp, 5);
    }

    @Test
    public void getHeight() {
        skyManager.setHeight(5);
        int temp = skyManager.getHeight();
        assertEquals(temp, 5);
    }

    @Test
    public void setHeight() {
        skyManager.setHeight(6);
        int temp = skyManager.getHeight();
        assertEquals(temp, 6);
    }

    @Test
    public void getRate() {
        skyManager.setRate(2);
        float temp = skyManager.getRate();
        assertEquals(2, temp, 0.0);
    }

    @Test
    public void setRate() {
        skyManager.setRate(2);
        float temp = skyManager.getRate();
        assertEquals(2, temp, 0.0);

    }

    @Test
    public void getEnemyBulletsList() {
        List<Bullet> a = skyManager.getEnemyBulletsList();
        assertEquals(a.size(), 0);
        Aircraft enemy = new EnemyAircraft();
        Bullet bullet = new Bullet(enemy);
        a.add(bullet);
        assertEquals(a.size(), 1);
    }

    @Test
    public void getEnemyAirCraftsList() {
        List<EnemyAircraft> a = skyManager.getEnemyAirCraftsList();
        assertEquals(a.size(), 0);
        EnemyAircraft enemy = new EnemyAircraft();
        a.add(enemy);
        assertEquals(a.size(), 1);
    }

    @Test
    public void getMyBulletsList() {
        List<Bullet> a = skyManager.getMyBulletsList();
        assertEquals(a.size(), 0);
        Aircraft aircraft = new MyAircraft();
        Bullet bullet = new Bullet(aircraft);
        a.add(bullet);
        assertEquals(a.size(), 1);
    }

    @Test
    public void setMyAircraft() {
        MyAircraft myAircraft = new MyAircraft();
        skyManager.setMyAircraft(myAircraft);
        MyAircraft my = skyManager.getMyAircraft();
        assertNotNull(my);
    }

    @Test
    public void getMyAircraft() {
        MyAircraft myAircraft = new MyAircraft();
        skyManager.setMyAircraft(myAircraft);
        assertNotNull(skyManager.getMyAircraft());
    }

    @Test
    public void addEnemyAircraft() {
        EnemyAircraft enmey = new EnemyAircraft();
        skyManager.addEnemyAircraft(enmey);
        int size = skyManager.getEnemyAirCraftsList().size();
        assertEquals(size, 1);
    }

    @Test
    public void removeEnemyAircraft() {
        EnemyAircraft enmey = new EnemyAircraft();
        skyManager.addEnemyAircraft(enmey);
        skyManager.removeEnemyAircraft(enmey);
        int size = skyManager.getEnemyAirCraftsList().size();
        assertEquals(size, 0);

    }

    @Test
    public void addMyBulletsList() {
        EnemyAircraft enmey = new EnemyAircraft();
        Bullet bullet = new Bullet(enmey);
        skyManager.addMyBulletsList(bullet);
        skyManager.addMyBulletsList(bullet);
        int size = skyManager.getMyBulletsList().size();
        assertEquals(2, size);
    }

    @Test
    public void removeMyBulletsList() {
        EnemyAircraft enmey = new EnemyAircraft();
        Bullet bullet = new Bullet(enmey);
        skyManager.addMyBulletsList(bullet);
        skyManager.addMyBulletsList(bullet);
        skyManager.removeMyBulletsList(bullet);
        int size = skyManager.getMyBulletsList().size();
        assertEquals(1, size);
    }

    @Test
    public void addEnemyBulletsList() {
        EnemyAircraft enmey = new EnemyAircraft();
        Bullet bullet = new Bullet(enmey);
        skyManager.addEnemyBulletsList(bullet);
        skyManager.addEnemyBulletsList(bullet);
        int size = skyManager.getEnemyBulletsList().size();
        assertEquals(2, size);
    }

    @Test
    public void removeEnemyBulletsList() {
        EnemyAircraft enmey = new EnemyAircraft();
        Bullet bullet = new Bullet(enmey);
        skyManager.addEnemyBulletsList(bullet);
        skyManager.addEnemyBulletsList(bullet);
        skyManager.removeEnemyBulletsList(bullet);
        int size = skyManager.getEnemyBulletsList().size();
        assertEquals(1, size);
    }

    @Test
    public void addTime() {
        skyManager.addTime();
        int a = skyManager.getMTimeLeftInMillis();
        assertEquals(1000, a);
    }

    @Test
    public void getMTimeLeftInMillis() {
        skyManager.addTime();
        skyManager.addTime();
        skyManager.addTime();
        int a = skyManager.getMTimeLeftInMillis();
        assertEquals(3000, a);
    }

    @Test
    public void getBackground() {
        Background background = new Background();
        skyManager.setBackground(background);
        Background a = skyManager.getBackground();
        assertEquals(a, background);
    }

    @Test
    public void setBackground() {
        Background background = new Background();
        skyManager.setBackground(background);
        Background a = skyManager.getBackground();
        assertEquals(a, background);
    }

    @Test
    public void isRunning() {
        assertTrue(skyManager.isRunning());
    }

    @Test
    public void stopRunning() {
        skyManager.stopRunning();
        assertFalse(skyManager.isRunning());
    }


}