package fall2018.csc2017.gameCentre.two_zero_four_eight;

import java.io.Serializable;

import fall2018.csc2017.gameCentre.GameTile;

/**
 * Playing tile for 2048, a subclass of Game Tile, with additional (x, y) coordinates attribute.
 */
class TwoTile extends GameTile implements Serializable {
    /**
     * x-coordinate of the tile
     */
    private int x;
    /**
     * y-coordinate of the tile
     */
    private int y;

    /**
     * Empty constructor, default ID = 0
     */
    TwoTile() {
        super();
    }

    /**
     * Constructor for num tile.
     *
     * @param ID of the tile.
     */
    TwoTile(int ID) {
        setId(ID);
    }

    /**
     * Setter for TwoTile.
     *
     * @param x x-coordinate on the 4x4 board.
     * @param y y-coordinate on the 4x4 board.
     */
    TwoTile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Comparing the ID between tiles.
     *
     * @param other TwoTile object.
     * @return if this tile has the same ID as other tile.
     */
    boolean equals(TwoTile other) {
        return this.getId() == other.getId();
    }

    /**
     * Getter for x-coordinate on the 4x4 board.
     *
     * @return x-coordinate on the 4x4 board.
     */
    int getX() {
        return x;
    }

    /**
     * Getter for y-coordinate on the 4x4 board.
     *
     * @return y-coordinate on the 4x4 board.
     */
    int getY() {
        return y;
    }
}

