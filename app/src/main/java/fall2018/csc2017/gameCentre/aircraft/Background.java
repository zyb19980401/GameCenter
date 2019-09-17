package fall2018.csc2017.gameCentre.aircraft;

import fall2018.csc2017.gameCentre.R;

/**
 * The background class.
 */
public class Background extends Flyable implements Runnable {
    /**
     * The constructor of Background.
     */
    Background() {
        setWidth(getSkyManager().getWidth());
        setHeight(getSkyManager().getHeight()*2);//background height is twice of the height of screen
        setImage(R.mipmap.background);
        setImage(R.mipmap.background);
        setX(0);
        setY(-getSkyManager().getHeight());
        new Thread(this).start();
    }

    @Override
    public void run() {
        //this controls the background moving downwards all the time
        while (getSkyManager().isRunning()) {
            System.out.println("我是TOP" + getRectangle().top);
            System.out.println("我是BOT" + getRectangle().bottom);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getRectangle().top + 2 <= 0) {
                setY(getRectangle().top + 2);
            } else {
                setY(-getSkyManager().getHeight());
            }
        }
    }
}
