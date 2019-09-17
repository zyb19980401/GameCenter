package fall2018.csc2017.gameCentre.slidingtiles;

import android.content.Context;
import android.widget.Toast;

/**
 * The controller of movement.
 */
class MovementController {
    /**
     * The empty BoardManager.
     */
    private BoardManager BoardManager = null;

    /**
     * The default constructor.
     */
    MovementController() {
    }

    /**
     * Set BoardManager.
     *
     * @param STBoardManager: the boardManager of slidingTile
     */
    void setSTBoardManager(BoardManager STBoardManager) {
        this.BoardManager = STBoardManager;
    }

    /**
     * Make text when puzzle solved or invalid move applied.
     *
     * @param context: the context; position: the position of white tile.
     */
    void processTapMovement(Context context, int position) {
        if (BoardManager.isValidTap(position)) {
            BoardManager.touchMove(position);
            if (BoardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
