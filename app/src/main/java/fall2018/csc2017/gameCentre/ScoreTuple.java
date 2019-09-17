package fall2018.csc2017.gameCentre;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Locale;

/**
 * a score class.
 */
public class ScoreTuple implements Comparable<ScoreTuple>, Serializable {
    /**
     * the player's name
     */
    private final String userName;

    /**
     * the player's score
     */
    private final int score;
    /**
     * the player's GameType
     */
    private String GameType;


    String getUserName() {
        return userName;
    }

    String getGameType() {
        return GameType;
    }

    /**
     * @param userName username
     * @param score    user's score
     * @param GameType GameType
     *                 the tuple that stores name,score, and GameType
     */
    public ScoreTuple(String userName, int score, String GameType) {
        this.userName = userName;
        this.score = score;
        this.GameType = GameType;
    }

    /**
     * return score.
     *
     * @return score.
     */
    public int getScore() {
        return score;
    }

    /**
     * To string Method
     */
    public String toString() {
        return String.format(Locale.US, "(" + "username:" + "%s  " + "Score:" + "%d  " + "Gametype:" + "%s  " + ")", userName, score, GameType);
    }

    /**
     * The comparator of this Score tuple
     */
    @Override
    public int compareTo(@NonNull ScoreTuple tuple2) {
        return Integer.compare(this.score, tuple2.score);
    }

    /**
     * The method to check the size of tuples
     *
     * @param other the other tuple
     */

    boolean GreaterThan(ScoreTuple other) {
        return this.score > other.score;
    }
}