package fall2018.csc2017.gameCentre;

import java.io.Serializable;

/**
 * The tile's super class
 */
public class GameTile implements Serializable {

    /**
     * The unique id.
     */
    private int id = 0;

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the tile id.
     *
     * @param id the tile id
     */
    public void setId(int id) {
        this.id = id;
    }

}
