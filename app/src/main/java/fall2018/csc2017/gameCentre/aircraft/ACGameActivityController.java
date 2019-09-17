package fall2018.csc2017.gameCentre.aircraft;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

public class ACGameActivityController extends Observable implements Observer {
    //The coordinates when the screen is pressed
    private float x;
    private float y;

    //The coordinates of myAirCraft when the screen is pressed
    private float myAircraftX;
    private float myAircraftY;


    /**
     * A new skyManager.
     */
    private static SkyManager skyManager;

    /**
     * A new onTouchListener.
     */
    private View.OnTouchListener onTouchListener;

    /**
     * The constructor.
     */
    @SuppressLint("ClickableViewAccessibility")
    ACGameActivityController() {
        skyManager = new SkyManager();
        skyManager.addObserver(this);
        new Thread(skyManager).start();
        createTimer();
        setOnTouchListener();
    }


    /**
     * @return return onTouchListener.
     */
    View.OnTouchListener getOnTouchListener() {
        return onTouchListener;
    }


    /**
     * @return return a skyManager.
     */
    public static SkyManager getSkyManager() {
        return skyManager;
    }


    /**
     * Activate the onTouchListener.
     */
    private void setOnTouchListener() {
        System.out.println("i am background 4" + ACGameActivityController.getSkyManager().getBackground());
        onTouchListener = (view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                view.performClick();
                x = motionEvent.getX();
                y = motionEvent.getY();
                myAircraftX = skyManager.getMyAircraft().getRectangle().left;
                myAircraftY = skyManager.getMyAircraft().getRectangle().top;
            }
            System.out.println("i am background 5" + ACGameActivityController.getSkyManager().getBackground());
            float newX = myAircraftX + motionEvent.getX() - x;
            float newY = myAircraftY + motionEvent.getY() - y;
            // my aircraft can't fly out of the screen
            if (newX >= skyManager.getWidth() - skyManager.getMyAircraft().getWidth() / 2) {
                newX = skyManager.getWidth() - skyManager.getMyAircraft().getWidth() / 2;
            }
            if (newX <= -skyManager.getMyAircraft().getWidth() / 2) {
                newX = -skyManager.getMyAircraft().getWidth() / 2;
            }
            if (newY >= skyManager.getHeight() - skyManager.getMyAircraft().getHeight() / 2) {
                newY = skyManager.getHeight() - skyManager.getMyAircraft().getHeight() / 2;
            }
            if (newY <= -skyManager.getMyAircraft().getHeight() / 2) {
                newY = -skyManager.getMyAircraft().getHeight() / 2;
            }

            skyManager.getMyAircraft().setX(newX);
            skyManager.getMyAircraft().setY(newY);
            return true;
        };
    }

    /**
     * create a new timer.
     * this is according to a post from stack overflow.
     * https://stackoverflow.com/questions/1877417/how-to-set-a-timer-in-android
     */
    private void createTimer() {
        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds
        handler.postDelayed(new Runnable() {
            public void run() {
                if (skyManager.isRunning()){
                skyManager.addTime();
                handler.postDelayed(this, delay);}}
        }, delay);
    }


    /**
     * Stop running the whole game.
     */
    void stopRunning() {
        skyManager.stopRunning();
    }


    @Override
    public void update(Observable o, Object arg) {
        if (!skyManager.isRunning()) {
            setChanged();
            notifyObservers();
        }


    }

}
