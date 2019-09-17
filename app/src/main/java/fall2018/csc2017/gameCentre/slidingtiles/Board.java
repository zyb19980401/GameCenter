package fall2018.csc2017.gameCentre.slidingtiles;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * The sliding STTiles board.
 */
public class Board extends Observable implements Serializable, Iterable<STTile> {

    /**
     * @return return complexity of the board
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * @return return steps moved
     */
    int getNumStep() {
        return numStep;
    }

    /**
     * The number of rows.
     */
    private int complexity = 4;

    /**
     * @return return the list STTiles.
     */
    STTile[][] getSTTiles() {
        return STTiles;
    }

    /**
     * The STTiles on the board in row-major order.
     */
    private STTile[][] STTiles = new STTile[complexity][complexity];

    /**
     * reset number of step to 0.
     */
    void resetNumStep() {
        this.numStep = 0;
    }

    /**
     * The number of total steps moved.
     */
    private int numStep = 0;

    /**
     * A new board of STTiles in row-major order.
     * Precondition: len(STTiles) == complexity * complexity
     *
     * @param STTiles    the STTiles for the board
     * @param complexity the complexity of the board.
     */
    Board(List<STTile> STTiles, int complexity) {
        Iterator<STTile> iter = STTiles.iterator();
        setComplexity(complexity);

        for (int row = 0; row != this.complexity; row++) {
            for (int col = 0; col != this.complexity; col++) {
                this.STTiles[row][col] = iter.next();
            }
        }

    }

    /**
     * setFinalScore the complexity.
     *
     * @param complexity the complexity of this board.
     */
    public void setComplexity(int complexity) {
        this.complexity = complexity;
        STTiles = new STTile[this.complexity][this.complexity];
    }

    @Override
    public @NonNull
    Iterator<STTile> iterator() {
        return new BoardIterator();
    }

    /**
     * Iterate STTiles on the STBoard.
     */
    private class BoardIterator implements Iterator<STTile> {
        /**
         * The index of the next tile on the STBoard.
         */
        int nextIndex = 0;

        @Override
        public boolean hasNext() {
            return nextIndex != complexity * complexity;
        }

        @Override
        public STTile next() {
            STTile result = STTiles[nextIndex / complexity][nextIndex % complexity];
            nextIndex++;
            return result;
        }
    }

    /**
     * Return the number of STTiles on the board.
     *
     * @return the number of STTiles on the board
     */
    int numTiles() {
        return complexity * complexity;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    STTile getTile(int row, int col) {
        return STTiles[row][col];
    }

    /**
     * Swap the STTiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        STTile STTile1 = this.getTile(row1, col1);
        STTile STTile2 = this.getTile(row2, col2);
        this.STTiles[row1][col1] = STTile2;
        this.STTiles[row2][col2] = STTile1;
        numStep += 1;
        setChanged();
        notifyObservers(numStep);
    }

}
