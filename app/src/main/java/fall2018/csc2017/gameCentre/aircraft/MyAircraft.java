package fall2018.csc2017.gameCentre.aircraft;


import fall2018.csc2017.gameCentre.R;

/**
 * The MyAircraft class.
 */
public class MyAircraft extends Aircraft {

    /**
     * The number of enemy crafts killed.
     */
    int numKilled;

    /**
     * The constructor of the MyAircraft class.
     */
    MyAircraft() {
        super();
        setHeight(200 * getSkyManager().getRate());
        setWidth(200 * getSkyManager().getRate());
        this.setHp(3);
        setX(getSkyManager().getWidth() / 2 - this.getWidth() / 2);
        setY(getSkyManager().getHeight() * 0.7f - this.getHeight() / 2);
        setImage(R.mipmap.myaircraft);
//        getSkyManager().setMyAircraft(this);
        new Thread(this).start();
    }

    /**
     * add one to number of enemy crafts killed.
     */
    void addNumKilled() {
        numKilled++;
    }


    @Override
    public void run() {
        while (getSkyManager().isRunning()) {
            super.run();
            try {
                // check hit
                HitByEnemyAircraft();
                HitByEnemyBullet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * A helper function to check if my aircraft is hit by enemy bullet..
     */
    private void HitByEnemyBullet() {
        for (int i = 0; i < getSkyManager().getEnemyBulletsList().size(); i++) {
            Bullet enemyBullet = getSkyManager().getEnemyBulletsList().get(i);
            if (this.isHitBy(enemyBullet)) {
                this.setHp(this.getHp() - 1);
                enemyBullet.setRunning(false);
                if (this.getHp() == 0) {
                    getSkyManager().stopRunning();
                }
                break;
            }

        }
    }

    /**
     * A helper function to check if my aircraft is hit by enemy craft.
     */
    private void HitByEnemyAircraft() {
        for (int i = 0; i < getSkyManager().getEnemyAirCraftsList().size(); i++) {
            EnemyAircraft enemyAircraft = getSkyManager().getEnemyAirCraftsList().get(i);
            if (this.isHitBy(enemyAircraft)) {
                enemyAircraft.setHp(0);
                this.setHp(this.getHp() - 1);
                this.numKilled++;
                if (this.getHp() == 0) {
                    getSkyManager().stopRunning();
                }
                break;
            }
        }
    }
}
