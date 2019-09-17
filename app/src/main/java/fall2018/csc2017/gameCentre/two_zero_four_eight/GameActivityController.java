package fall2018.csc2017.gameCentre.two_zero_four_eight;
import android.content.Context;
import android.widget.Toast;
import java.util.Observable;
import java.util.Observer;
import fall2018.csc2017.gameCentre.LoginActivityController;
import fall2018.csc2017.gameCentre.Saver;
import fall2018.csc2017.gameCentre.ScoreManager;
import fall2018.csc2017.gameCentre.ScoreTuple;
import fall2018.csc2017.gameCentre.Session;

/**
 * The controller for Game Activity.
 */
public class GameActivityController extends Observable implements Observer {
    /**
     * The current context.
     */
    private Context context;
    /**
     * The game session.
     */
    private Session session;// = LoginActivityController.session;
    /**
     * The saver to load file.
     */
    private Saver saver;
    /**
     * The score tuple to be saved.
     */
    private ScoreTuple finalScore;
    /**
     * If a new board needed to be set up for a new game.
     */
    private boolean newGame;
    /**
     * The current board manager.
     */
    private TwoBoardManager twoBoardManager;

    /**
     * Constructor for the controller.
     *
     * @param context the game context
     */
    GameActivityController(Context context) {
        this.context = context;
        saver = new Saver(context);
        this.newGame = true;
        session = LoginActivityController.session;
        twoBoardManager = (TwoBoardManager) saver.loadFromFile(session.getTwoSaveFile());
        twoBoardManager.getTwoBoard().addObserver(this);
    }

    /**
     * Another Constructor for the controller.
     *
     * @param twoBoardManager the game context
     */
    GameActivityController(TwoBoardManager twoBoardManager) {
        this.twoBoardManager = twoBoardManager;
        saver = new Saver(context);
        this.newGame = true;
    }

    /**
     * The execution done on the board after each movement or button pressed.
     */
    void execution() {
        normalMoveHelper();
        twoBoardManager.setUsedUndo(false);
        twoBoardManager.updateScore();
        setNewGame(false);
        twoBoardManager.checkGameOver();
        // first time game over, record score
        if (twoBoardManager.isGameOver() && (twoBoardManager.getOverCount() == 1)) {
            gameOverHelper();
        }
        setChanged();
        notifyObservers();
        saver.saveToFile(twoBoardManager, session.getTwoSaveFile());
    }

    /**
     * A helper function helps to smooth out execution method when a normal move is conducted (not restarted nor undo).
     */
    private void normalMoveHelper() {
        // not restarted & not undo => an action has been performed
        if (!twoBoardManager.hasRestarted() && !twoBoardManager.hasUsedUndo()) {
            // not first time game over, do nothing
            if (twoBoardManager.isGameOver() && (twoBoardManager.getOverCount() > 1)) {
                Toast.makeText(context, "Game Over, Please Restart", Toast.LENGTH_SHORT).show();
                twoBoardManager.resetStepsAfterUndo();
            }
            // invalid move, do nothing
            else if (!twoBoardManager.isValidMove()) {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            // valid move, add new tile
            else {
                twoBoardManager.addNewNum();
                twoBoardManager.addStepsAfterUndo();
            }
        } else {
            twoBoardManager.setRestart(false);
        }
    }

    /**
     * A helper function helps to smooth out execution method when a Game Over status has been reached.
     */
    private void gameOverHelper() {
        Toast.makeText(context, "Game Over, Please Restart", Toast.LENGTH_SHORT).show();
        setFinalScore();
        ScoreManager manager = ScoreManager.getScoreManager(context);
        manager.addScore(LoginActivityController.session.getName(), finalScore);
        manager.checkTop("2048", finalScore);
        twoBoardManager.resetStepsAfterUndo();
    }

    /**
     * Start a game.
     */
    void startGame() {
        if (newGame && !StartingActivity.isLoadedGame()) {
            twoBoardManager = new TwoBoardManager();
            twoBoardManager.addNewNum();
            twoBoardManager.addNewNum();
            // remove the extra terms.
            twoBoardManager.getRecentTwoBoards().remove(0);
            twoBoardManager.resetStepsAfterUndo();

            //deep copying of new current board as last board
            TwoBoard lastTwoBoard = new TwoBoard();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    lastTwoBoard.setTile(i, j, twoBoardManager.getTwoBoard().getTile(i, j));
                }
            }
            twoBoardManager.recordBoard(lastTwoBoard);
            saver.saveToFile(twoBoardManager, session.getTwoSaveFile());
            setChanged();
            notifyObservers();
        }
        StartingActivity.setLoadedGame(false);
    }

    /**
     * Get the score of the current board manager.
     *
     * @return current score
     */
    int getBMScore() {
        return twoBoardManager.getCurrentScore();
    }

    /**
     * Enable the undo button iff it is more than one step after each undo or not game over yet.
     *
     * @return the availability of the undo button
     */
    boolean enableUndo() {
        return (twoBoardManager.getStepsAfterUndo() >= 1) && (!twoBoardManager.isGameOver());
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    /**
     * Action taken by the player.
     *
     * @param s action name
     */
    void action(String s) {
        switch (s) {
            case "left":
                twoBoardManager.managerController.scrollLeft(twoBoardManager.getTwoBoard());
                break;
            case "right":
                twoBoardManager.managerController.scrollRight(twoBoardManager.getTwoBoard());
                break;
            case "up":
                twoBoardManager.managerController.scrollUp(twoBoardManager.getTwoBoard());
                break;
            case "down":
                twoBoardManager.managerController.scrollDown(twoBoardManager.getTwoBoard());
                break;
        }
    }

    /**
     * Set the final score tuple for saving.
     */
    private void setFinalScore() {
        finalScore = new ScoreTuple(LoginActivityController.session.getName(), twoBoardManager.getCurrentScore(), "2048");
    }

    /**
     * Setter for restart status.
     */
    void setBMRestart() {
        twoBoardManager.setRestart(true);
    }

    /**
     * Setter for undo status.
     */
    void setBMUsedUndo() {
        twoBoardManager.setUsedUndo(true);
    }

    /**
     * Undo the action.
     */
    void BMUndo() {
        twoBoardManager.undo();
    }

    /**
     * Check the game over status.
     *
     * @return if the game is currently over
     */
    boolean isGameOver() {
        return twoBoardManager.isGameOver();
    }

    /**
     * Setter for the new game indicator
     *
     * @param newGame if it is a new game
     */
    void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }

    /**
     * Getter for the newGame
     *
     * @return newGame
     */
    boolean getNewGame() {
        return newGame;
    }

    /**
     * Getter for the board manager.
     *
     * @return the current board manager
     */
    TwoBoardManager getTwoBoardManager() {
        return this.twoBoardManager;
    }
}
