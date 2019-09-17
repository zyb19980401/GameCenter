package fall2018.csc2017.gameCentre.two_zero_four_eight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a board, includes keeping track of changes in Board and Tiles with zero ID,
 * changes in score and calculate current score,
 * calling BMController, undo actions, checking for a game over.
 */
class TwoBoardManager implements Serializable {

    /**
     * The current playing board.
     */
    private TwoBoard twoBoard;
    /**
     * The BMController helps to execute actions.
     */
    TwoBoardManagerController managerController;
    /**
     * A list keep track of all changes of the board.
     */
    private List<TwoBoard> recentTwoBoards;
    /**
     * A list keep track of all changes in score after each action has taken.
     */
    private List<Integer> scoreList;
    /**
     * The current score.
     */
    private int currentScore;
    /**
     * If the board has just been restarted. New game is considered as a restart.
     */
    private boolean restart;
    /**
     * If the board has just used Undo.
     */
    private boolean usedUndo;
    /**
     * Record the number of GameOver reached, in order to terminate some functions.
     */
    private int overCount = 0;
    /**
     * Tracking the steps after undo for setting undo button availability.
     */
    private int stepsAfterUndo;
    /**
     * If the current game is over.
     */
    private boolean gameOver;

    /**
     * Constructor for BM, setting all tiles as zero tiles.
     */
    TwoBoardManager() {
        this.twoBoard = new TwoBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.getTwoBoard().setTile(i, j, new TwoTile(0));
            }
        }
        recentTwoBoards = new ArrayList<>();
        scoreList = new ArrayList<>();
        currentScore = 0;
        restart = false;
        usedUndo = false;
        managerController = new TwoBoardManagerController(this);
        gameOver = false;
    }



    /*
    We found an easy way to add non-zero ID tiles to the board
    by introducing its complement set - the zero ID tiles set.

    Adapted from:
    https://github.com/plter/Android2048GameLesson/blob/master/code/ide/AndroidStudio/Game2048Publish/app/src/main/java/com/jikexueyuan/game2048publish/GameView.java
     */

    /**
     * Adding new number tile to the board.
     */
    void addNewNum() {
        if (recentTwoBoards.size() > 1) {
            recentTwoBoards.remove(recentTwoBoards.size() - 1);
        }

        List<TwoTile> zeroTwoTiles = new ArrayList<>();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (twoBoard.getTile(x, y).getId() <= 0) {
                    zeroTwoTiles.add(new TwoTile(x, y));
                }
            }
        }
        TwoTile twoTileToAdd = zeroTwoTiles.remove((int) (Math.random() * zeroTwoTiles.size()));
        // 15% chance of adding a 4 tile, 85% chance of adding a 2 tile
        twoBoard.setTile(twoTileToAdd.getX(), twoTileToAdd.getY(), new TwoTile(Math.random() > 0.15 ? 2 : 4));

        //deep copying of new current board as last board
        TwoBoard newTwoBoard = new TwoBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newTwoBoard.setTile(i, j, twoBoard.getTile(i, j));
            }
        }
        recordBoard(newTwoBoard);
    }

    /**
     * Update the current score, by summing up all the score changes in scoreList.
     */
    void updateScore() {
        int sum = 0;
        for (int i : scoreList) {
            sum += i;
        }
        currentScore = sum;
    }

    /**
     * Record the board in boardList.
     *
     * @param lastTwoBoard the board to be added.
     */
    void recordBoard(TwoBoard lastTwoBoard) {
        recentTwoBoards.add(lastTwoBoard);
    }

    /**
     * Check if the movement is valid.
     *
     * @return if the movement is valid.
     */
    boolean isValidMove() {

        boolean valid = false;

        if (recentTwoBoards.size() <= 1) {
            return true;
        }

        //valid move if the board has changed
        if (!((recentTwoBoards.get(recentTwoBoards.size() - 1)).equals(recentTwoBoards.get(recentTwoBoards.size() - 2)))) {
            valid = true;
        }

        //delete the duplicate board in boardList if the move is invalid
        if (!valid && overCount < 2) {
            if (recentTwoBoards.size() > 2) {
                undo();
            }
        }
        return valid;
    }

    /**
     * Undo movement. Delete previous board and scoreList.
     * Pointer set to the board before.
     */
    void undo() {
        if (recentTwoBoards.size() >= 2) {
            //deep copying of new current twoBoard as last twoBoard
            TwoBoard lastTwoBoard = new TwoBoard();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    lastTwoBoard.setTile(i, j, recentTwoBoards.get(recentTwoBoards.size() - 2).getTile(i, j));
                }
            }
            this.twoBoard = lastTwoBoard;
            recentTwoBoards.remove(recentTwoBoards.size() - 1);
            scoreList.remove(scoreList.size() - 1);
        }
    }

    /**
     * Check if the board can still be moved (Game Over).
     * Game over: for all tiles,
     * none of the tiles has the same ID as its adjacent (top, bottom, left, right) tiles
     * OR if there is no zero tiles.
     */
    void checkGameOver() {
        boolean gameOver = true;

        for (int position = 0; position < TwoBoard.getNumTiles(); position++) {
            int row = position / 4;
            int col = position % 4;
            int thisTileID = twoBoard.getTile(row, col).getId();

            // if adjacent tile has the same ID, or if the tile selected is a zero tile
            TwoTile above = row == 0 ? null : twoBoard.getTile(row - 1, col);
            TwoTile below = row == 4 - 1 ? null : twoBoard.getTile(row + 1, col);
            TwoTile left = col == 0 ? null : twoBoard.getTile(row, col - 1);
            TwoTile right = col == 4 - 1 ? null : twoBoard.getTile(row, col + 1);
            if ((thisTileID == 0)
                    || (below != null && below.getId() == thisTileID)
                    || (above != null && above.getId() == thisTileID)
                    || (left != null && left.getId() == thisTileID)
                    || (right != null && right.getId() == thisTileID)) {
                gameOver = false;
            }

        }

        if (gameOver) {
            this.gameOver = true;
            overCount++;
        }
    }

    /**
     * Getter for the board list.
     *
     * @return the list of all boards
     */
    List<TwoBoard> getRecentTwoBoards() {
        return recentTwoBoards;
    }

    /**
     * Getter for the scoreList
     *
     * @return the list with all changes of score
     */
    List<Integer> getScoreList() {
        return scoreList;
    }

    /**
     * Getter for current score.
     *
     * @return the current score.
     */
    int getCurrentScore() {
        return currentScore;
    }

    /**
     * Getter for the restart status.
     *
     * @return if the board has just restarted
     */
    boolean hasRestarted() {
        return restart;
    }

    /**
     * Setter for the restart status.
     *
     * @param restart if the board has just restarted
     */
    public void setRestart(boolean restart) {
        this.restart = restart;
    }

    /**
     * Getter for the undo status.
     *
     * @return if the board has just used undo.
     */
    boolean hasUsedUndo() {
        return usedUndo;
    }

    /**
     * Setter for the undo status.
     *
     * @param usedUndo if the board has just used undo
     */
    void setUsedUndo(boolean usedUndo) {
        this.usedUndo = usedUndo;
    }

    /**
     * Getter for the GameOver count.
     *
     * @return the number of GameOver the board has reached
     */
    int getOverCount() {
        return overCount;
    }

    /**
     * Reset the step after each undo or at new game.
     */
    void resetStepsAfterUndo() {
        this.stepsAfterUndo = 0;
    }

    /**
     * The getter for stepsAfterUndo
     *
     * @return stepsAfterUndo
     */
    int getStepsAfterUndo() {
        return stepsAfterUndo;
    }

    /**
     * Add the step after undo after each valid movement.
     */
    void addStepsAfterUndo() {
        this.stepsAfterUndo++;
    }

    boolean isGameOver() {
        return gameOver;
    }

    /**
     * Getter for the managing board.
     *
     * @return the board under management.
     */
    TwoBoard getTwoBoard() {
        return twoBoard;
    }
}
