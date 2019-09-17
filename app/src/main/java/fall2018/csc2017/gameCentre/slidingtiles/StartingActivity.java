package fall2018.csc2017.gameCentre.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import fall2018.csc2017.gameCentre.GameCentreActivity;
import fall2018.csc2017.gameCentre.R;
import fall2018.csc2017.gameCentre.TopScoreboardActivity;
import fall2018.csc2017.gameCentre.UserScoreboardActivity;


/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {

    /**
     * The StartingActivityController that controls this activity.
     */
    private StartingActivityController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_);
        Button loadButton = findViewById(R.id.LoadButton);
        controller = new StartingActivityController(this);
        loadButton.setEnabled(controller.checkLoad());
        addStartButtonListener();
        addLoadButtonListener();
        addSettingButtonListener();
        addUserScoreboardListener();
        addTop10ScoreboardListener();
    }

    /**
     * Activate the start button.
     */
    private void addSettingButtonListener() {
        Button settingButton = findViewById(R.id.SettingButton);
        settingButton.setOnClickListener(arg0 -> {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);

        });
    }


    /**
     * Activate the start button
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(v -> {
            controller.setBoardManager(new BoardManager(SettingActivity.selectedComplexity));
            controller.save();
            System.out.println(" zybzybzyb StartingActivity" + (controller.getBoardManager().getBoard().getSTTiles())[2][2].getId());
            switchToGame();

        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(v -> {
            makeToastLoadedText();
            switchToGame();
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        controller.load();
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        startActivity(tmp);
    }


    /**
     * Activate the UseScoreBoard button.
     */
    private void addUserScoreboardListener() {
        Button UserScoreboard = findViewById(R.id.userscoreboard);
        UserScoreboard.setOnClickListener(v -> {
            Intent tmp = new Intent(this, UserScoreboardActivity.class);
            startActivity(tmp);
        });
    }

    /**
     * Activate the TopTenScoreBoard button.
     */
    private void addTop10ScoreboardListener() {
        Button TopScoreboard = findViewById(R.id.topscoreboard);
        TopScoreboard.setOnClickListener(v -> {
            Intent tmp = new Intent(this, TopScoreboardActivity.class);
            startActivity(tmp);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent tmp = new Intent(this, GameCentreActivity.class);
        startActivity(tmp);

    }
}