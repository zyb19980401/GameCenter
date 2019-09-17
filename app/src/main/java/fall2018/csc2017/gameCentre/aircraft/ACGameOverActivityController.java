package fall2018.csc2017.gameCentre.aircraft;

import android.content.Context;

import java.util.Locale;

import fall2018.csc2017.gameCentre.ScoreManager;
import fall2018.csc2017.gameCentre.ScoreTuple;
import fall2018.csc2017.gameCentre.Session;

public class ACGameOverActivityController {

    /**
     * A new skyManager.
     */
    private static SkyManager skyManager;

    /**
     * The context.
     */
    private Context context;

    /**
     * constructor of ACGameOverActivityController
     */
    ACGameOverActivityController(Context context) {
        this.context = context;
    }


    /**
     * @param skyManager setter for Sky_manager
     */
    static void setSkyManager(SkyManager skyManager) {
        ACGameOverActivityController.skyManager = skyManager;
    }

    /**
     * @return return the skyManager.
     */
    public static SkyManager getSkyManager() {
        return skyManager;
    }


    /**
     * @return the_string_of how many u have killed
     */
    String killString() {
        return "You have killed  " + skyManager.getMyAircraft().numKilled + "  enemy";
    }

    /**
     * @return the string of how long u have survived.
     */
    String timeString() {
        return "You have survived  " + getTimeString();
    }

    /**
     * @return the score that u get
     */
    String scoreString() {
        return "You have got " + Integer.toString(getScore()) + " points !!!!";

    }

    /**
     * @param name name of the player
     *             generate the scoreTuple and add to both lists.
     */
    void setScoreTuple(String name) {
        ScoreTuple scoretuple = new ScoreTuple(name, getScore(), "AirCraft");
        ScoreManager manager = ScoreManager.getScoreManager(context);
        manager.addScore(Session.getSession().getName(), scoretuple);
        manager.checkTop("AirCraft", scoretuple);
    }

    /**
     * update time.
     */
    private String getTimeString() {
        int minutes = (skyManager.getMTimeLeftInMillis() / 1000) / 60;
        int seconds = (skyManager.getMTimeLeftInMillis() / 1000) % 60;

        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    /**
     * @return return the score int
     */
    private int getScore() {
        return skyManager.getMyAircraft().numKilled * skyManager.getMTimeLeftInMillis() / 1000;
    }


}
