package fall2018.csc2017.gameCentre;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;


/**
 * Manage UserAccounts
 * serialization
 * this is adapted from a post of tutorialspoint
 * https://www.tutorialspoint.com/java/java_serialization.htm
 */
class UserAccountManager {

    public void setMap(Map<String, UserAccount> map) {
        this.map = map;
    }

    public Map<String, UserAccount> getMap() {
        return map;
    }

    /**
     * the map to save UserAccount
     */
    private Map<String, UserAccount> map;

    /**
     * the filename.
     */
    private static final String fileName = "UserAccount.ser";

    /**
     * the saver to save file.
     */
    private Saver saver;

    /**
     * the userAccountManager.
     */
    private static UserAccountManager userAccountManager;


    /**
     * Manage the UserAccounts.
     *
     * @param context Interface to global information about the application environment.
     */
    private UserAccountManager(Context context) {
        saver = new Saver(context);
        loadFromFile();
    }

    /**
     * @return return userAccountManager
     * Citation:
     * use singleton here
     * https://www.geeksforgeeks.org/singleton-class-java/
     */
    static UserAccountManager getUserAccountManager(Context context) {
        if (userAccountManager == null) {
            userAccountManager = new UserAccountManager(context);
        }
        return userAccountManager;
    }

    /**
     * Add a new user, and save the new UserAccount file.
     *
     * @param name     username
     * @param password password
     */
    void addUserAccount(String name, String password) {
        if (!(map.containsKey(name))) {
            map.put(name, new UserAccount(name, password));
        }
        saver.saveToFile(map, fileName);
    }


    /**
     * Load the saved file.
     */
    @SuppressWarnings("unchecked")
    void loadFromFile() {
        map = (HashMap<String, UserAccount>) saver.loadFromFile(fileName);
        if(map == null){
            map = new HashMap<>();
        }

    }


    /**
     * @param name an user's username
     * @return check whether the saved user accounts file has a specific username.
     */
    boolean contains(String name) {
        return (map.get(name) != null);
    }


    /**
     * @param username an user's username
     * @param password this user's password
     * @return whether an user's username matches corresponding password.
     */
    Boolean CheckMatchness(String username, String password) {
        return map.get(username).getPassword().equals(password);
    }


}
