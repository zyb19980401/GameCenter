package fall2018.csc2017.gameCentre.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import fall2018.csc2017.gameCentre.R;
import fall2018.csc2017.gameCentre.Session;

/**
 * a winner activity that appears when u win the game
 */
public class WinnerActivity extends AppCompatActivity {
    private WinnerActivityController controller = new WinnerActivityController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner);
        controller.addScore(Session.getSession().getName(), BoardManager.finalScore);
        controller.addTop("Tiles", BoardManager.finalScore);
        TextView textView = findViewById(R.id.ScoreTextView);
        String string = controller.get_string(BoardManager.finalScore.getScore());
        textView.setText(string);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, StartingActivity.class);
        startActivity(intent);
    }


}
