package fall2018.csc2017.gameCentre.slidingtiles;

import android.content.Context;


import fall2018.csc2017.gameCentre.ScoreManager;
import fall2018.csc2017.gameCentre.ScoreTuple;

/**
 * A winner activity controller.
 */
class WinnerActivityController {
    /**
     * the context
     */
    private Context context;

    /**
     * The constructor of this class.
     */
    WinnerActivityController(Context context) {
        this.context = context;
    }

    void addScore(String name, ScoreTuple score) {
        ScoreManager manager = ScoreManager.getScoreManager(context);
        manager.addScore(name, score);
    }

    /**
     * add the score to ScoreManager's Top 10 ScoreBoard
     */
    void addTop(String GameType, ScoreTuple score) {
        ScoreManager manager = ScoreManager.getScoreManager(context);
        manager.checkTop(GameType, score);
    }

    /**
     * get the String for text view
     **/
    String get_string(int score) {
        return "Your score is: " + score;
    }


}
