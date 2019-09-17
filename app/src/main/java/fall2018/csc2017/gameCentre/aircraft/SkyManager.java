package fall2018.csc2017.gameCentre.aircraft;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * The SkyManager class.
 */
class SkyManager extends Observable implements Runnable {

    /**
     * The SkyManager class.
     */
    private String complexity = ACSettingActivity.getSelectedDifficulty();

    /**
     * @return return the width of screen
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set width of screen .
     */
    void setWidth(int width) {
        this.width = width;
    }

    /**
     * The Width of screen .
     */
    private int width;

    /**
     * @return return the height of screen .
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set height of screen .
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * The height of the screen.
     */
    private int height;

    /**
     * @return return the rate.
     */
    float getRate() {
        return rate;
    }

    /**
     * set rate.
     */
    void setRate(float rate) {
        this.rate = rate;
    }

    /**
     * The rate in order to adapt different screens.
     */
    private float rate;

    /**
     * @return return the list of enemy bullets
     */
    List<Bullet> getEnemyBulletsList() {
        return enemyBulletsList;
    }

    /**
     * A list of enemy's bullets
     */
    private List<Bullet> enemyBulletsList =
            Collections.synchronizedList(new ArrayList<>()); //所有飞行物的集合,添加进这个集合才能被画出来

    /**
     * @return return the list of enemy bullets
     */
    List<EnemyAircraft> getEnemyAirCraftsList() {
        return enemyAirCraftsList;
    }

    /**
     * A list of enemy AirCrafts
     */
    private List<EnemyAircraft> enemyAirCraftsList =
            Collections.synchronizedList(new ArrayList<>()); //所有飞行物的集合,添加进这个集合才能被画出来

    /**
     * @return return the bullet list of my bullets
     */
    List<Bullet> getMyBulletsList() {
        return myBulletsList;
    }

    /**
     * The list of my bullets
     */
    private List<Bullet> myBulletsList = Collections.synchronizedList(new ArrayList<>());

    /**
     * Set my aircraft.
     */
    void setMyAircraft(MyAircraft myAircraft) {
        this.myAircraft = myAircraft;
    }

    /**
     * @return return my AirCraft
     */
    MyAircraft getMyAircraft() {
        return myAircraft;
    }

    /**
     * My AirCraft.
     */
    private MyAircraft myAircraft;

    /**
     * Add enemy Aircraft to the list enemyAirCraftsList
     */
    void addEnemyAircraft(EnemyAircraft enemyAircraft) {
        enemyAirCraftsList.add(enemyAircraft);
    }

    /**
     * remove specific enemy Aircraft.
     */
    void removeEnemyAircraft(EnemyAircraft enemyAircraft) {
        enemyAirCraftsList.remove(enemyAircraft);
    }

    /**
     * Add my bullets to the list enemyAirCraftsList
     */
    void addMyBulletsList(Bullet myBullet) {
        myBulletsList.add(myBullet);
    }

    /**
     * Remove my bullets from the list myBulletsList
     */
    void removeMyBulletsList(Bullet myBullet) {
        myBulletsList.remove(myBullet);
    }

    /**
     * Add enemy Aircraft to the list enemyBulletsList
     */
    void addEnemyBulletsList(Bullet enemyBullet) {
        enemyBulletsList.add(enemyBullet);
    }

    /**
     * remove enemy Aircraft from the list enemyBulletsList
     */
    void removeEnemyBulletsList(Bullet enemyBullet) {
        enemyBulletsList.remove(enemyBullet);
    }


    /**
     * add constant time of 1000 milliseconds to time left.
     */
    void addTime() {
        mTimeLeftInMillis += 1000;
    }

    /**
     * @return return the time left
     */
    int getMTimeLeftInMillis() {
        return mTimeLeftInMillis;
    }

    /**
     * The number of milliseconds.
     */
    private int mTimeLeftInMillis;

    /**
     * @return return the background.
     */
    public Background getBackground() {
        return background;
    }

    /**
     * the backgroud.
     */
    private Background background;

    /**
     * Set background.
     */
    public void setBackground(Background NewBackGround) {
        background = NewBackGround;
    }

    /**
     * Set running.
     */
    private boolean running = true;

    /**
     * check if it's running.
     */
    boolean isRunning() {
        return this.running;
    }

    /**
     * notify observer when not running.
     */
    void stopRunning() {
        this.running = false;
        setChanged();
        notifyObservers();
    }

    @Override
    public void run() {
        while (running) {
            // add new enemy Aircraft every 1500 or 800ms according to the complexity.
            try {
                if (complexity.equals("Easy")) {
                    Thread.sleep(1500);

                    new EnemyAircraft();
                } else {
                    Thread.sleep(800);

                    new EnemyAircraft();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
