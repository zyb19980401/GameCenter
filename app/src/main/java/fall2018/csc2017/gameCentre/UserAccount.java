package fall2018.csc2017.gameCentre;


import java.io.Serializable;

/**
 * An UserAccount with username and password.
 */
public class UserAccount implements Serializable {

    /**
     * the username
     */
    private String name;

    /**
     * the password.
     */
    private String password;

    /**
     * The constructor of the class UserAccount.
     */
    UserAccount(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * return name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * return the password.
     *
     * @return password.
     */

    public String getPassword() {
        return password;
    }


}

