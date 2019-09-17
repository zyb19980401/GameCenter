package fall2018.csc2017.gameCentre.two_zero_four_eight;

import java.io.Serializable;
import java.util.Observable;

/**
 * The 2048 board.
 */
class TwoBoard extends Observable implements Serializable {

    /**
     * The Tiles on the board.
     */
    private TwoTile[][] twoTiles;

    /**
     * Constructor.
     */
    TwoBoard() {
        twoTiles = new TwoTile[4][4];
    }

    /**
     * Set a specific twoTile at a specific location on the board.
     *
     * @param x       x-coordinate of the twoTile on the board.
     * @param y       y-coordinate of the twoTile on the board.
     * @param twoTile the twoTile object to be setFinalScore.
     */
    void setTile(int x, int y, TwoTile twoTile) {
        twoTiles[x][y] = twoTile;
        setChanged();
        notifyObservers();
    }

    /**
     * Return the specific tile at a specific location on the board.
     *
     * @param x x-coordinate of the tile on the board.
     * @param y y-coordinate of the tile on the board.
     * @return The tile object of interest.
     */
    public TwoTile getTile(int x, int y) {
        return twoTiles[x][y];
    }

    /**
     * The getter for twoTile
     *
     * @return return twoTiles
     */
    TwoTile[][] getTwoTiles() {
        return twoTiles;
    }

    /**
     * Boards are equal if the Tile ID at each coordinate is the same.
     *
     * @param obj another TwoBoard object.
     * @return if the Tile ID at each coordinate is the same.
     */
    boolean equals(TwoBoard obj) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((this.getTile(i, j) != null) && (obj.getTile(i, j) != null) &&
                        (this.getTile(i, j).equals(obj.getTile(i, j)))) {
                    count++;
                } else {
                    return false;
                }
            }
        }
        return count == 16;
    }

    /**
     * Getter for the number of tiles in a board, pre-defined as 16.
     *
     * @return the number of tiles in a board
     */
    static int getNumTiles() {
        return 4 * 4;
    }
}
