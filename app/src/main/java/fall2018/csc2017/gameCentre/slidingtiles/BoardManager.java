package fall2018.csc2017.gameCentre.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import fall2018.csc2017.gameCentre.LoginActivityController;
import fall2018.csc2017.gameCentre.ScoreTuple;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager implements Serializable {

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * The List of recentSteps.
     */
    List<int[]> recentSteps;

    /**
     * The number of steps allowed to undo with default value of 3.
     */
    static int numSteps = 3;

    /**
     * indicate whether the user has uploaded picture or not.
     */
    static boolean SelfDefinedIndicator = false;

    /**
     * The final score.
     */
    static ScoreTuple finalScore;

    /**
     * @return return time passed
     */
    int getMTimeLeftInMillis() {
        return mTimeLeftInMillis;
    }

    /**
     * time passed
     */
    void setMTimeLeftInMillis(int a) {
        mTimeLeftInMillis = a;
    }


    /**
     * add time
     */
    void addTime() {
        this.mTimeLeftInMillis += 1000;
    }

    void SetMTimeLeftInMillis(int mTimeLeftInMillis) {
        this.mTimeLeftInMillis = mTimeLeftInMillis;
    }

    /**
     * The number of milliseconds.
     */
    private int mTimeLeftInMillis;

    /**
     * reset number of undo to a specific number.
     */
    void setNumUndo(int undo) {
        this.numUndo = undo;
    }

    /**
     * @return return number of UnDos.
     */
    int getNumUndo() {
        return numUndo;
    }

    /**
     * the number of undo.
     */
    private int numUndo;

    /**
     * indicator of whether or not the board is solved
     */
    boolean isSolved = false;


    /**
     * Manage a board that has been pre-populated.
     *
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
        recentSteps = new ArrayList<>(numSteps);


    }


    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Manage a new shuffled board.
     */
    BoardManager(int complexity) {
        List<STTile> tiles = new ArrayList<>();
        final int numTiles = complexity * complexity;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if (!SelfDefinedIndicator) {
                tiles.add(new STTile(tileNum, complexity));
            } else {
                tiles.add(new SelfDefinedSTTile(tileNum, complexity));
            }
        }

        this.board = new Board(tiles, complexity);
        shuffleboard(this.board);
        recentSteps = new ArrayList<>(numSteps);
    }

    /**
     * shuffle the board makes it always be solvable
     *
     * @param board: this board
     *               Citation: java Random of selecting an random item from list
     *               https://stackoverflow.com/questions/12487592/randomly-select-an-item-from-a-list
     *               java Random of selecting a random number
     *               https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java
     */
    private void shuffleboard(Board board) {
        // position of the white tile which is updated after every swap
        int position = board.getComplexity() * board.getComplexity() - 1;
        // randomly generate a number
        Random rand = new Random();
        int n = rand.nextInt(100) + 2;
        int i = 1;
        while (i < n) {
            // get row,col number of the white tile.
            int row = position / board.getComplexity();
            int col = position % board.getComplexity();
            // check whether 4 of white tile's surrounding tiles are available
            STTile above = row - 1 == -1 ? null : board.getTile(row - 1, col);
            STTile below = row + 1 == board.getComplexity() ? null : board.getTile(row + 1, col);
            STTile left = col - 1 == -1 ? null : board.getTile(row, col - 1);
            STTile right = col + 1 == board.getComplexity() ? null : board.getTile(row, col + 1);
            // add available positions to the list
            List<Integer> positions = new ArrayList<>();
            if (above != null) {
                positions.add(position - board.getComplexity());
            }
            if (below != null) {
                positions.add(position + board.getComplexity());
            }
            if (left != null) {
                positions.add(position - 1);
            }
            if (right != null) {
                positions.add(position + 1);
            }
            // random get a position to be swapped with the white tile.
            Random myRandom = new Random();
            Integer random = positions.get(myRandom.nextInt(positions.size()));
            int NewRow = random / board.getComplexity();
            int NewCol = random % board.getComplexity();
            // swap two tiles and update white tile
            board.swapTiles(row, col, NewRow, NewCol);
            board.resetNumStep();
            position = random;
            i++;
        }
    }


    /**
     * Return whether the tiles are in row-major order.
     */
    boolean puzzleSolved() {
        int i = 0;
        Iterable<STTile> iterable = board;
        for (STTile tile : iterable) {
            ++i;
            if (tile.getId() != i) {
                return false;
            }
        }
        isSolved = true;
        return true;
    }

    /**
     * record this step
     *
     * @param thisStep the step to be recorded.
     */
    private void recordStep(int[] thisStep) {
        if (recentSteps.size() < numSteps) {
            recentSteps.add(thisStep);
        } else {
            recentSteps.remove(0);
            recentSteps.add(thisStep);
        }
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */


    boolean isValidTap(int position) {

        int row = position / board.getComplexity();
        int col = position % board.getComplexity();
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        STTile above = row == 0 ? null : board.getTile(row - 1, col);
        STTile below = row == board.getComplexity() - 1 ? null : board.getTile(row + 1, col);
        STTile left = col == 0 ? null : board.getTile(row, col - 1);
        STTile right = col == board.getComplexity() - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }


    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     */
    void touchMove(int position) {

        int row = position / board.getComplexity();
        int col = position % board.getComplexity();
        int blankId = board.numTiles();
        if (this.isValidTap(position)) {
            int[][] positionsSurrounded = {{row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};
            for (int i = 0; i < 4; i++) {
                int selectedRow = positionsSurrounded[i][0];
                int selectedCol = positionsSurrounded[i][1];
                if (selectedRow < board.getComplexity() && selectedRow >= 0
                        && selectedCol < board.getComplexity() && selectedCol >= 0) {
                    STTile tile = board.getTile(selectedRow, selectedCol);
                    if (tile.getId() == blankId) {
                        board.swapTiles(row, col, selectedRow, selectedCol);
                        int[] thisStep = {row, col, selectedRow, selectedCol};
                        recordStep(thisStep);
                    }

                }

            }
        }
    }

    /**
     * undo the previous step.
     *
     * @param steps the list of steps to undo.
     */
    void undo(List<int[]> steps) {
        if (!steps.isEmpty()) {

            int[] lastStep = steps.get(steps.size() - 1);
            board.swapTiles(lastStep[0], lastStep[1], lastStep[2], lastStep[3]);
            steps.remove(steps.size() - 1);
            numUndo += 1;
        }

    }


    /**
     * calculate final score.
     */
    void calculate() {

        finalScore = new ScoreTuple(LoginActivityController.session.getName(), get_score(), "Tiles");
    }


    int get_score() {
        int total = 500;

        total += Math.round(-0.15 * getMTimeLeftInMillis() / 1000 - 0.25 * board.getNumStep() - 3 * numUndo - 10 * board.getComplexity());
        return total;
    }
}







