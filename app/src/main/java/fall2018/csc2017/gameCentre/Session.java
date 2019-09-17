package fall2018.csc2017.gameCentre;

/**
 * The information for each user.
 */
public class Session {

    /**
     * @return this session.
     */
    public static Session getSession() {
        return session;
    }

    /**
     * The information for the login user being managed
     */
    private static Session session;

    /**
     * The username for the login user.
     */
    private String name;

    /**
     * @return The username for the login user.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The file for SlidingTile.
     */
    public String getSTSaveFile() {
        return STSaveFile;
    }

    /**
     * @return the name for game 2048.
     */
    public String getTwoSaveFile() {
        return TwoSaveFile;
    }


    /**
     * The SaveFile for game slidingTiles.
     */
    private String STSaveFile;

    /**
     * The SaveFile for game 2048.
     */
    private String TwoSaveFile;

    /**
     * The constructor of session.
     *
     * @param username username
     */
    private Session(String username) {
        this.name = username;
        this.STSaveFile = this.name + "SlidingTiles.ser";
        this.TwoSaveFile = this.name + "2048.ser";
    }

    /**
     * Return the information for the login user.
     *
     * @param username username
     * @return The information for the login user
     */
    static Session login(String username) {
        if (session == null) {
            session = new Session(username);
        }

        return session;
    }

    /**
     * Empty the login user's information when logging out.
     */
    static void logout() {
        session = null;
    }

}
