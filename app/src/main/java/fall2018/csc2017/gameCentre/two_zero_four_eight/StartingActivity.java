package fall2018.csc2017.gameCentre.two_zero_four_eight;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.File;
import fall2018.csc2017.gameCentre.GameCentreActivity;
import fall2018.csc2017.gameCentre.LoginActivityController;
import fall2018.csc2017.gameCentre.R;
import fall2018.csc2017.gameCentre.Saver;
import fall2018.csc2017.gameCentre.Session;
import fall2018.csc2017.gameCentre.TopScoreboardActivity;
import fall2018.csc2017.gameCentre.UserScoreboardActivity;

public class StartingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * The session contains current user.
     */
    public static Session session;

    /**
     * The twoSaver used to load files
     */
    private Saver twoSaver;

    private TwoBoardManager twoBoardManager;

    private String[] mode = {"regular", "Pokemon!"};

    static String GAMEMODE = "regular";

    public static void setLoadedGame(boolean loadedGame) {
        StartingActivity.loadedGame = loadedGame;
    }

    private static boolean loadedGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_starting);
        Button loadButton = findViewById(R.id.TwoLoadButton);
        twoSaver = new Saver(this);
        session = LoginActivityController.session;
        loadButton.setEnabled(checkLoad(session.getTwoSaveFile()));
        addTwoStartButtonListener();
        addTwoLoadButtonListener();
        addTwoUserScoreboardListener();
        addTwoTop10ScoreboardListener();
        Spinner modeSpinner = findViewById(R.id.modeSpinner);
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mode);
        modeSpinner.setOnItemSelectedListener(this);
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(modeAdapter);
    }

    //Performing action onItemSelected and onNothing selected.

    /** implement a switch selection in the onItemSelected method for spinner
     *  citation:
     *  this is according to the second and third answers from a forum of stack overflow on May 28,2015
     *  https://stackoverflow.com/questions/20151414/how-can-i-use-onitemselected-in-android
     */

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                GAMEMODE = arg0.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    /**
     * Activate the newGame button.
     */
    private void addTwoStartButtonListener() {
        Button startButton = findViewById(R.id.TwoStartButton);
        startButton.setOnClickListener(v -> {
            loadedGame = false;
            twoBoardManager = new TwoBoardManager();
            twoSaver.saveToFile(twoBoardManager, session.getTwoSaveFile());
            switchToGame();
        });
    }

    /**
     * Activate the load button.
     */
    private void addTwoLoadButtonListener() {
        Button loadButton = findViewById(R.id.TwoLoadButton);
        loadButton.setOnClickListener(v -> {
            loadedGame = true;
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
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        startActivity(tmp);
    }

    /**
     * @param fileName the save file for game.
     * @return Check whether there will be a load file.
     */
    private boolean checkLoad(String fileName) {
        boolean load;
        File file = this.getFileStreamPath(fileName);
        if (file == null || !file.exists()) {
            load = false;
        } else {
            TwoBoardManager twoBoardManager = (TwoBoardManager) twoSaver.loadFromFile(LoginActivityController.session.getTwoSaveFile());
            if (twoBoardManager == null) {
                load = false;
            } else {
                load = !(twoBoardManager.isGameOver());
            }
        }
        return load;
    }

    /**
     * Activate the UseScoreBoard button.
     */
    private void addTwoUserScoreboardListener() {
        Button UserScoreboard = findViewById(R.id.twouserscoreboard);
        UserScoreboard.setOnClickListener(v -> {
            Intent tmp = new Intent(this, UserScoreboardActivity.class);
            startActivity(tmp);
        });
    }

    /**
     * Activate the TopTenScoreBoard button.
     */
    private void addTwoTop10ScoreboardListener() {
        Button TopScoreboard = findViewById(R.id.Twotopscoreboard);
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

    public static boolean isLoadedGame() {
        return loadedGame;
    }
}