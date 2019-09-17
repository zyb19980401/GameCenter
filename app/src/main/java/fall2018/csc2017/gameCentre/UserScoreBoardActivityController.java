package fall2018.csc2017.gameCentre;

import java.util.ArrayList;

/**
 * a controller class for UserScoreBoardActivity
 */
class UserScoreBoardActivityController {
    /**
     * The game type.
     */
    private final String gameType;

    /**
     * a List of ScoreTuple.
     */
    private final ArrayList<ScoreTuple> temp;

    /**
     * a Activity for UserScoreboard, which is only for one user
     */
    UserScoreBoardActivityController(String gameType, ArrayList<ScoreTuple> temp) {
        this.gameType = gameType;
        this.temp = temp;
    }

    /**
     * return an ArrayList contains all scoreTuple with the same GameType of gameType.
     */
    @SuppressWarnings("unchecked")
    ArrayList helper() {
        if (temp == null) {
            return null;
        }
        ArrayList<ScoreTuple> acc = new ArrayList();
        for (ScoreTuple i : temp) {
            if (i.getGameType().equals(gameType)) {
                acc.add(i);
            }
        }
        return acc;


    }
}
