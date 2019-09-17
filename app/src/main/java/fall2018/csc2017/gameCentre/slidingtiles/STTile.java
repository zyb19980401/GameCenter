package fall2018.csc2017.gameCentre.slidingtiles;


import android.support.annotation.NonNull;


import fall2018.csc2017.gameCentre.GameTile;
import fall2018.csc2017.gameCentre.R;


/**
 * A TwoTile in a sliding tiles puzzle.
 */
public class STTile extends GameTile implements Comparable<STTile> {

    /**
     * The background id to find the tile image.
     */
    private int background;


    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * A tile with a background id; look up and setFinalScore the id.
     *
     * @param backgroundId background id
     */
    STTile(int backgroundId, int complexity) {
        super();
        setId(backgroundId + 1);
        int[] tileList = {R.drawable.tile_1, R.drawable.tile_2, R.drawable.tile_3,
                R.drawable.tile_4, R.drawable.tile_5, R.drawable.tile_6, R.drawable.tile_7,
                R.drawable.tile_8, R.drawable.tile_9, R.drawable.tile_10, R.drawable.tile_11,
                R.drawable.tile_12, R.drawable.tile_13, R.drawable.tile_14, R.drawable.tile_15,
                R.drawable.tile_16, R.drawable.tile_17, R.drawable.tile_18, R.drawable.tile_19,
                R.drawable.tile_20, R.drawable.tile_21, R.drawable.tile_22, R.drawable.tile_23,
                R.drawable.tile_24, R.drawable.tile_blank};
        if ((complexity == 3 && backgroundId == 8) || (complexity == 4 && backgroundId == 15)
                || (complexity == 5 && backgroundId == 24)) {
            background = R.drawable.tile_blank;
        } else {
            background = tileList[backgroundId];
        }
    }

    @Override
    public int compareTo(@NonNull STTile o) {
        return o.getId() - this.getId();
    }
}
