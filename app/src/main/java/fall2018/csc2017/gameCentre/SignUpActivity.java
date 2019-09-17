package fall2018.csc2017.gameCentre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Button;
import android.widget.EditText;
/**
 * A sign up screen that allows adding more users.
 */
public class SignUpActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    /**
     * Username
     */
    private EditText keyInput;

    /**
     * Password
     */
    private EditText valueInput;

    private SignUpActivityController controller;// = new SignUpActivityController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        keyInput = findViewById(R.id.KeyInput);
        valueInput = findViewById(R.id.ValueInput);
        CheckBox checkbox = findViewById(R.id.showpassword);
        valueInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkbox.setOnCheckedChangeListener(this);
        controller = new SignUpActivityController(this);
        addFinishSignUpButtonListener();
    }

    /**
     * Activate the finish sign up button.
     * citation:
     * get input EditText value by user to TextView
     * this is according to the second answer from a forum of stack overflow on Feb 15, 2012.
     * https://stackoverflow.com/questions/4396376/how-to-get-edittext-value-and-display-it-on-screen-through-textview/4396400
     */
    private void addFinishSignUpButtonListener() {
        Button finishSignUpButton = findViewById(R.id.FinishSignUpButton);
        finishSignUpButton.setOnClickListener(v -> {
            String username = keyInput.getText().toString();
            String password = valueInput.getText().toString();
            //Username is taken.
            controller.signUp(username, password);

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
