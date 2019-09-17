
package fall2018.csc2017.gameCentre.slidingtiles;

import android.content.Context;
import android.os.Handler;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import fall2018.csc2017.gameCentre.LoginActivityController;
import fall2018.csc2017.gameCentre.Saver;
import fall2018.csc2017.gameCentre.Session;

/**
 * The controller of game activity.
 */
public class GameActivityController extends Observable implements Observer {
    BoardManager getBoardManager() {
        return boardManager;
    }

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The saver which save files.
     */
    private Saver saver;

    /**
     * @return return time left.
     */
    String getTimeLeftFormatted() {
        return timeLeftFormatted;
    }

    /**
     * String represents time left.
     */
    private String timeLeftFormatted;

    /**
     * @return return the steps.
     */
    String getStep() {
        return step;
    }

    /**
     * Steps moved.
     */
    private String step;

    /**
     * @return return the complexity of game.
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * the complexity of the game.
     */
    private int complexity;

    /**
     * The information for login user.
     */
    private Session session = LoginActivityController.session;


    /**
     * Constructor for GameActivityController
     */
    GameActivityController(Context context) {
        saver = new Saver(context);
        session = LoginActivityController.session;
        boardManager = (BoardManager) saver.loadFromFile(session.getSTSaveFile());
        System.out.println("bbbbbbbbb" + session.getSTSaveFile());
        System.out.println(boardManager == null);
        complexity = boardManager.getBoard().getComplexity();
        if (BoardManager.SelfDefinedIndicator) {
            SelfDefinedSTTile.split(SettingActivity.selectedImage, complexity, complexity);
        }
        boardManager.getBoard().addObserver(this);

    }

    /**
     * Another Constructor for GameActivityController
     *
     * @param boardManager boardManager
     */
    GameActivityController(BoardManager boardManager) {

        this.boardManager = boardManager;
        complexity = boardManager.getBoard().getComplexity();
        if (BoardManager.SelfDefinedIndicator) {
            SelfDefinedSTTile.split(SettingActivity.selectedImage, complexity, complexity);
        }
        boardManager.getBoard().addObserver(this);
    }


    /**
     * Check if the gamer wins the game.
     */
    boolean checkWin() {
        if (boardManager.puzzleSolved()) {
            boardManager.isSolved = true;
            boardManager.calculate();
            boardManager.getBoard().deleteObserver(this);
            return true;
        }
        return false;

    }

    /**
     * create a new timer.
     * this is according to a post from stack overflow.
     * https://stackoverflow.com/questions/1877417/how-to-set-a-timer-in-android
     */
    void createTimer() {
        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds
        handler.postDelayed(new Runnable() {
            public void run() {
                generateTimeLeftFormatted();
                updateTimerText();
                setChanged();
                notifyObservers();
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    /**
     * generate the time left.
     */
    void generateTimeLeftFormatted() {
        boardManager.addTime();
        int minutes = (boardManager.getMTimeLeftInMillis() / 1000) / 60;
        int seconds = (boardManager.getMTimeLeftInMillis() / 1000) % 60;
        timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    /**
     * Save the board manager.
     */
    void save() {
        saver.saveToFile(boardManager, session.getSTSaveFile());
    }

    /**
     * @return return the board.
     */
    Board getBoard() {
        return boardManager.getBoard();
    }

    /**
     * Apply undo.
     */
    void undo() {
        boardManager.undo(boardManager.recentSteps);
    }


    @Override
    public void update(Observable o, Object arg) {
        step = "step: " + arg.toString();
        setChanged();
        //updateTileButtons();
        updateTimerText();
        setChanged();
        notifyObservers();
    }


    /**
     * Update time.
     */
    private void updateTimerText() {
        int minutes = (boardManager.getMTimeLeftInMillis() / 1000) / 60;
        int seconds = (boardManager.getMTimeLeftInMillis() / 1000) % 60;

        timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        setChanged();
        notifyObservers();

    }

}
