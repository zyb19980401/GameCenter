package fall2018.csc2017.gameCentre;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.gameCentre.Exceptions.ExistedException;
import fall2018.csc2017.gameCentre.Exceptions.NoInputException;

class SignUpActivityController {


    private Context context;
    /**
     * The user account manager.
     */
    private UserAccountManager manager;

    SignUpActivityController(Context context) {
        this.context = context;
        manager = UserAccountManager.getUserAccountManager(context);
        manager.loadFromFile();
    }


    void signUp(String username, String password) {
        boolean result = manager.contains(username);
        try {
            if (username.trim().length() > 0 && password.trim().length() > 0) {
                if (!result) {
                    manager.addUserAccount(username, password);
                    Toast.makeText(context, "Sign up successfully", Toast.LENGTH_SHORT).show();
                } else {
                    throw new ExistedException();
                }
            } else {
                throw new NoInputException();
            }
        } catch (ExistedException e) {
            Toast.makeText(context, "Please choose a different Username", Toast.LENGTH_SHORT).show();
        } catch (NoInputException e) {
            Toast.makeText(context, "Please enter a valid Username/Password", Toast.LENGTH_SHORT).show();
        }
    }


}

