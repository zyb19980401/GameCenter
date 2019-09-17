package fall2018.csc2017.gameCentre;

import android.content.Context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * the class that manage all the score tuples.
 * serialization
 * this is adapted from a post of tutorialspoint
 * https://www.tutorialspoint.com/java/java_serialization.htm
 */
public class ScoreManager implements Serializable {

    Map<String, ArrayList> getUserMap() {
        return userMap;
    }

    /**
     * user map which is the Hash map that takes Username as Key, an ArrayList of score tuples as value
     */
    private Map<String, ArrayList> userMap = new HashMap<>(); //for Slicing tiles

    Map<String, ArrayList> getTopMap() {
        return topMap;
    }

    /**
     * Top10 map which is the Hash map that takes GameType as Key, an ArrayList of score tuples as value
     */
    private Map<String, ArrayList> topMap = new HashMap<>();

    /**
     * the file that stores user hash map.
     */
    private static final String userFileName = "UserScore.ser";

    /**
     * the file that stores top 10 score hash map.
     */
    private static final String topFileName = "TopScore.ser";


    /**
     * the userAccountManager.
     */
    private static ScoreManager scoreManager;

    /**
     * the saver to save file.
     */
    private Saver saver;


    private ScoreManager(Context context) {
        saver = new Saver(context);
    }

    public static ScoreManager getScoreManager(Context context) {
        if (scoreManager == null) {
            scoreManager = new ScoreManager(context);
        }
        return scoreManager;
    }


    @SuppressWarnings("unchecked")
    public void addScore(String username, ScoreTuple tt) {
        if (!(userMap.containsKey(username))) {
            ArrayList<ScoreTuple> a = new ArrayList();
            userMap.put(username, a);
            a.add(tt);
        } else {
            userMap.get(username).add(tt);
            Collections.sort(userMap.get(username));
        }
        saver.saveToFile(userMap, userFileName);


    }

    /**
     * add score to the top10 hash map and stores it into the file
     */
    @SuppressWarnings("unchecked")
    public void checkTop(String GameType, ScoreTuple tt) {
        if (!(topMap.containsKey(GameType))) {
            ArrayList<ScoreTuple> a = new ArrayList();
            topMap.put(GameType, a);
            a.add(tt);
        } else {
            ArrayList<ScoreTuple> temp = topMap.get(GameType);
            if (temp.size() < 10) {
                temp.add(tt);
                Collections.sort(temp);
            } else {
                Collections.sort(temp);
                if (tt.GreaterThan(temp.get(0))) {
                    temp.remove(0);
                    temp.add(tt);
                    Collections.sort(temp);
                }
            }


        }
        saver.saveToFile(topMap, topFileName);
    }

    /**
     * load the top 10 hash map from the file to the Class's topmap variable
     */
    @SuppressWarnings("unchecked")
    void loadFromTopFile() {
        topMap = (Map<String, ArrayList>) saver.loadFromFile(topFileName);
    }

    /**
     * load the hash map from the file to the Class's map varaible.
     */
    @SuppressWarnings("unchecked")
    void loadFromUserFile() {
        userMap = (Map<String, ArrayList>) saver.loadFromFile(userFileName);
    }
}