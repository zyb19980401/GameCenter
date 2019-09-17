package fall2018.csc2017.gameCentre;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


/**
 * A login screen that offers login via password.
 */
public class LoginActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    /**
     * The input Username
     */
    private EditText keyInput;


    /**
     * The input Password
     */
    private EditText valueInput;


    /**
     * The information for login user.
     */
    public static Session session;

    /**
     * The information for login user.
     */
    //private UserAccountManager manager;
    private LoginActivityController controller;// = new LoginActivityController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        keyInput = findViewById(R.id.UNInput);
        valueInput = findViewById(R.id.PWInput);
        CheckBox checkbox = findViewById(R.id.checkBox);
        valueInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkbox.setOnCheckedChangeListener(this);
        controller = new LoginActivityController(this);
        addSignInButtonListener();
        addSignUpButtonListener();
    }

    /**
     * Activate the sign in button.
     * citation:
     * get input EditText value by user to TextView
     * this is according to the second answer from a forum of stack overflow on Feb 15, 2012.
     * https://stackoverflow.com/questions/4396376/how-to-get-edittext-value-and-display-it-on-screen-through-textview/4396400
     */
    private void addSignInButtonListener() {
        controller.loadFromFile();
        Button signInButton = findViewById(R.id.SignInButton);
        signInButton.setOnClickListener(v -> {
            String username = keyInput.getText().toString();
            String password = valueInput.getText().toString();
            controller.login(username, password);
        });

    }

    /**
     * Activate the sign up button.
     */
    private void addSignUpButtonListener() {
        Button signUpButton = findViewById(R.id.SignUpButton);
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    /**
     * hide and show password when log in.
     */
    /* Citation:
     * Show/Hide Password in EditText in Android
     * https://medium.com/@droidbyme/show-hide-password-in-edittext-in-android-c4c3db44f734
     */
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            valueInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            valueInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}


