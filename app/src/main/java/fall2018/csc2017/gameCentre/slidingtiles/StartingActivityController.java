package fall2018.csc2017.gameCentre.slidingtiles;

import android.content.Context;

import java.io.File;

import fall2018.csc2017.gameCentre.LoginActivityController;
import fall2018.csc2017.gameCentre.Saver;
import fall2018.csc2017.gameCentre.Session;


/**
 * The controller of game activity.
 */
public class StartingActivityController {
    /**
     * The session.
     */
    private Session session;

    BoardManager getBoardManager() {
        return boardManager;
    }

    void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * The boardManager.
     */
    private BoardManager boardManager;

    /**
     * The context.
     */
    public Context context;

    private Saver saver;

    /**
     * The constructor of StartActivityController.
     */
    StartingActivityController(Context context) {
        this.session = LoginActivityController.session;
        this.context = context;
        saver = new Saver(context);
    }


    /**
     * check if the give fileName exists.
     */
    boolean checkLoad() {
        boolean load;
        File file = context.getFileStreamPath(session.getSTSaveFile());
        if (file == null || !file.exists()) {
            load = false;
        } else {
            BoardManager boardManager = (BoardManager) saver.loadFromFile(session.getSTSaveFile());
            if (boardManager == null) {
                load = false;
            } else {
                load = !boardManager.puzzleSolved();
            }

        }
        return load;
    }

    /**
     * Save the board manager to fileName.
     */
    void save() {
        saver.saveToFile(boardManager, session.getSTSaveFile());
    }

    /**
     * Load the board manager from fileName.
     */
    void load() {
        boardManager = (BoardManager) saver.loadFromFile(session.getSTSaveFile());
    }
}