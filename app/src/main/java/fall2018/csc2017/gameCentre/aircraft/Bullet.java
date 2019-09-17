package fall2018.csc2017.gameCentre.aircraft;

import fall2018.csc2017.gameCentre.R;

/**
 * The bullet class.
 */
class Bullet extends Flyable implements Runnable {

    /**
     * The aircraft.
     */
    private Aircraft aircraft;

    /**
     * A boolean to check if the bullet is from My or enemy's craft.
     */
    private boolean flyUp;

    /**
     * A boolean to check whether the bullet is moving.
     */
    private volatile boolean running = true;

    /**
     * @param aircraft the aircraft
     *                 The constructor of the bullet.
     */
    Bullet(Aircraft aircraft) {
        super();
        this.aircraft = aircraft;

        setHeight(50 * getSkyManager().getRate());
        setWidth(50 * getSkyManager().getRate());
        setImage(R.mipmap.background);
        setSpeed(6 * getSkyManager().getRate());
        setX(aircraft.getRectangle().left + aircraft.getWidth() / 2 - getWidth() / 2);
        if (aircraft instanceof MyAircraft) {
            setY(aircraft.getRectangle().top - getHeight() / 2);
        } else {
            setY(aircraft.getRectangle().bottom - getHeight() / 2);
        }
        if (aircraft instanceof MyAircraft) {
            flyUp = true;
            getSkyManager().addMyBulletsList(this);
        } else {
            flyUp = false;
            getSkyManager().addEnemyBulletsList(this);
        }
        new Thread(this).start();
    }

    /**
     * Set bullet moving or not.
     */
    void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (getSkyManager().isRunning() && running) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (flyUp) {
                setY(getRectangle().top - getSpeed());
                running = getRectangle().top + getHeight() > 0;
            } else {
                this.setSpeed(2 * getSkyManager().getRate());
                setY(getRectangle().top + getSpeed());
                running = getRectangle().top < getSkyManager().getHeight();
            }

        }
        if (aircraft instanceof MyAircraft) {
            getSkyManager().removeMyBulletsList(this);
        } else {
            getSkyManager().removeEnemyBulletsList(this);
        }
    }
}