package fall2018.csc2017.gameCentre.aircraft;

import fall2018.csc2017.gameCentre.R;

/**
 * The EnemyAircraft class.
 */
class EnemyAircraft extends Aircraft {

    /**
     * The sleep time.
     */
//    private long sd0 = (long) (Math.random() * 10);
    private long sd0 = (50);
    /**
     * The complexity selected.
     */
    private String complexity = ACSettingActivity.getSelectedDifficulty();

    /**
     * The constructor of EnemyAircraft.
     */
    EnemyAircraft() {
        super();
        setHeight(200 * getSkyManager().getRate());
        setWidth(200 * getSkyManager().getRate());
        // set the initial horizontal position of enemy craft appears randomly.
        setX((float) (Math.random() * (getSkyManager().getWidth() - getWidth())));
        // set the initial height out of screen
        setY(-getHeight());
        setImage(R.mipmap.enemyaircraft);
        setHp(1);
        getSkyManager().addEnemyAircraft(this);
        new Thread(this).start();

    }

    @Override
    public void run() {
        boolean hitTed = false;
        while (getSkyManager().isRunning()) {

            super.run();
            try {
                // check if the enemy craft is hit by bullets
                Thread.sleep(sd0);
                for (int i = 0; i < getSkyManager().getMyBulletsList().size(); i++) {
                    Bullet myBullet = getSkyManager().getMyBulletsList().get(i);
                    if (this.isHitBy(myBullet)) {
                        this.setHp(this.getHp() - 1);
                        getSkyManager().getMyAircraft().addNumKilled();
                        hitTed = true;
                        break;

                    }

                }
                // change movement speed when complexity changes.
                if (complexity.equals("Easy")) {
                    setY(getRectangle().top + 20 * getSkyManager().getRate());
                } else {
                    setY(getRectangle().top + 80 * getSkyManager().getRate());
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            boolean running = (getRectangle().top < getSkyManager().getHeight());
            if (!running || hitTed) break;
        }
        // enemy aircraft disappears if not run.
        getSkyManager().removeEnemyAircraft(this);


    }
}
