package fall2018.csc2017.gameCentre.aircraft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import fall2018.csc2017.gameCentre.GameCentreActivity;

import fall2018.csc2017.gameCentre.R;

import fall2018.csc2017.gameCentre.TopScoreboardActivity;
import fall2018.csc2017.gameCentre.UserScoreboardActivity;


/**
 * The initial activity for the sliding puzzle tile game.
 */
public class ACStartingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acstarting_);
        addStartButtonListener();
        addUserScoreboardListener();
        addTop10ScoreboardListener();
        addSettingListener();
    }

    /**
     * Activate the setting button.
     */
    private void addSettingListener() {
        Button setting = findViewById(R.id.settingButton);
        setting.setOnClickListener(v -> {
            Intent temp = new Intent(this, ACSettingActivity.class);
            startActivity(temp);
        });

    }


    /**
     * Activate the start button
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.button);
        startButton.setOnClickListener(v -> switchToGame());
    }

    /**
     * Switch to the GameActivity view to play the game.   //todo
     */
    private void switchToGame() {
        Intent temp = new Intent(this, ACGameActivity.class);
        startActivity(temp);
    }


    /**
     * Activate the UseScoreBoard button.
     */
    private void addUserScoreboardListener() {
        Button UserScoreboard = findViewById(R.id.acuserscoreboard);
        UserScoreboard.setOnClickListener(v -> {
            Intent temp = new Intent(this, UserScoreboardActivity.class);
            startActivity(temp);
        });
    }

    /**
     * Activate the TopTenScoreBoard button.
     */
    private void addTop10ScoreboardListener() {
        Button TopScoreboard = findViewById(R.id.actopScoreboard);
        TopScoreboard.setOnClickListener(v -> {
            Intent temp = new Intent(this, TopScoreboardActivity.class);
            startActivity(temp);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, GameCentreActivity.class);
        startActivity(temp);

    }
}
