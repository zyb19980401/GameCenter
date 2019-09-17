package fall2018.csc2017.gameCentre;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import fall2018.csc2017.gameCentre.aircraft.ACStartingActivity;
import fall2018.csc2017.gameCentre.two_zero_four_eight.StartingActivity;

/**
 * The activity for choosing games.
 */
public class GameCentreActivity extends AppCompatActivity {

    /**
     * Return string represents current game.
     */
    public static String getCurrent() {
        return current;
    }

    /**
     * Initiate an empty string.
     */
    static String current = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamecentre);
        addSTButtonListener();
        addACButtonListener();
        addTwoButtonListener();
        addLogoutButtonListener();
    }

    /**
     * Activate the TwoButton to starting 2048 game.
     */
    /*
    Learned how to resize my bitmap.
    Adapted from:
    https://stackoverflow.com/questions/7021578/resize-drawable-in-android
     */
    void addTwoButtonListener() {
        View view = findViewById(R.id.two_zero_four_eight_Button);
        ImageButton twoButton = (ImageButton) view;
        ImageView iv = (ImageView) view;
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.two_zero_four_eight);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 250, 250, true);
        iv.setImageBitmap(bMapScaled);
        twoButton.setOnClickListener(v -> {
            current = "2048";
            Intent intent = new Intent(this, StartingActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Activate the STButton to starting slidingTiles game.
     */
    void addSTButtonListener() {
        View view = findViewById(R.id.STButton);
        ImageButton STButton = (ImageButton) view;
        ImageView iv = (ImageView) view;
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.hqdefault);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 250, 250, true);
        iv.setImageBitmap(bMapScaled);
        STButton.setOnClickListener(v -> {
            current = "Tiles";
            Intent intent = new Intent(this, fall2018.csc2017.gameCentre.slidingtiles.StartingActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Activate the ACButton to starting Aircraft game.
     */
    void addACButtonListener() {
        View view = findViewById(R.id.AircraftButton);
        ImageButton ACButton = (ImageButton) view;
        ImageView iv = (ImageView) view;
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.aircraft);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 250, 250, true);
        iv.setImageBitmap(bMapScaled);
        ACButton.setOnClickListener(v -> {
            current = "AirCraft";
            Intent intent = new Intent(this, ACStartingActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Activate the log out button.
     */
    public void addLogoutButtonListener() {
        Button LogoutButton = findViewById(R.id.LogoutButton);
        LogoutButton.setOnClickListener(v -> {
            Session.logout();
            Intent tmp = new Intent(this, LoginActivity.class);
            startActivity(tmp);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Session.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
