package fall2018.csc2017.gameCentre.aircraft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import fall2018.csc2017.gameCentre.LoginActivityController;
import fall2018.csc2017.gameCentre.R;

/**
 * The AirCraft GameOver Activity class.
 */
public class ACGameOverActivity extends AppCompatActivity {
    private ACGameOverActivityController controller = new ACGameOverActivityController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACGameOverActivityController.setSkyManager(ACGameActivityController.getSkyManager());
        setContentView(R.layout.gameover);
        TextView kills = findViewById(R.id.givekills);
        TextView times = findViewById(R.id.givetime);
        TextView score = findViewById(R.id.givescore);
        kills.setText(controller.killString());
        times.setText(controller.timeString());
        score.setText(controller.scoreString());
        controller.setScoreTuple(LoginActivityController.session.getName());
    }


    @Override
    public void onBackPressed() {
        Intent temp = new Intent(this, ACStartingActivity.class);
        startActivity(temp);
        finish();
    }


}