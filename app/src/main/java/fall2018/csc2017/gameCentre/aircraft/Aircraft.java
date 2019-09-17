package fall2018.csc2017.gameCentre.aircraft;

/**
 * The Aircraft class.
 */
public class Aircraft extends Flyable implements Runnable {

    /**
     * The hp of each aircraft.
     */
    private int hp;

    /**
     * @return return hp of the aircraft.
     */
    int getHp() {
        return hp;
    }

    /**
     * set hp.
     */
    void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @param obj: any object extends flyable
     *             check if aircraft is hit by bullet or enemy craft.
     */
    boolean isHitBy(Flyable obj) {
        float px = 25 * getSkyManager().getRate();
        return checkLeftHit(px, this, obj) && checkTopHit(px, this, obj);

    }

    /**
     * A helper function.
     */
    boolean checkLeftHit(float px, Flyable objSelf, Flyable obj) {
        return objSelf.getRectangle().left - obj.getRectangle().left + px <= obj.getWidth()
                && obj.getRectangle().left - objSelf.getRectangle().left + 3 * px <= this.getWidth();
    }

    /**
     * A helper function.
     */
    boolean checkTopHit(float px, Flyable objSelf, Flyable obj) {
        return objSelf.getRectangle().top + px - obj.getRectangle().top <= obj.getHeight()
                && obj.getRectangle().top - objSelf.getRectangle().top + 3 * px <= objSelf.getHeight();
    }

    @Override
    public void run() {
        try {
            if (this instanceof MyAircraft) {
                Thread.sleep(200);
                new Bullet(this);
            } else {
//                Thread.sleep(1200);
//                new Bullet(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
