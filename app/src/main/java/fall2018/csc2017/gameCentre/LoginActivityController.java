package fall2018.csc2017.gameCentre;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import fall2018.csc2017.gameCentre.Exceptions.AuthenticationException;
import fall2018.csc2017.gameCentre.Exceptions.NoInputException;
import fall2018.csc2017.gameCentre.Exceptions.NoSuchAccountException;


/**
 * A login activity controller.
 */
public class LoginActivityController {


    /**
     * The information for login user.
     */
    public static Session session;

    private Context context;
    /**
     * The information for login user.
     */
    private UserAccountManager manager;

    LoginActivityController(Context context) {
        this.context = context;
        manager = UserAccountManager.getUserAccountManager(context);
    }

    void loadFromFile() {
        manager.loadFromFile();
    }


    void login(String username, String password) {
        try {
            if (username.trim().length() > 0 && password.trim().length() > 0) {
                if (manager.contains(username)) {
                    if (manager.CheckMatchness(username, password)) {
                        session = Session.login(username);
                        Intent tmp = new Intent(context, GameCentreActivity.class);
                        context.startActivity(tmp);
                    } else {
                        throw new AuthenticationException();
                    }
                } else {
                    throw new NoSuchAccountException();
                }
            } else {
                throw new NoInputException();
            }
        } catch (AuthenticationException e) {
            Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show();
        } catch (NoSuchAccountException e) {
            Toast.makeText(context, "No such User Found", Toast.LENGTH_SHORT).show();
        } catch (NoInputException e) {
            Toast.makeText(context, "Please enter a valid Username/Password", Toast.LENGTH_SHORT).show();
        }
    }

}


