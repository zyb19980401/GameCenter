package fall2018.csc2017.gameCentre.aircraft;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import java.util.Observable;
import java.util.Observer;

public class ACGameActivity extends AppCompatActivity implements Observer {

    private ACGameActivityController controller;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ACGameActivityController();
        GameView gameView = new GameView(this,ACGameActivityController.getSkyManager());
        setContentView(gameView);
        gameView.setOnTouchListener(controller.getOnTouchListener());
        controller.addObserver(this);
    }


    /**
     * check if the game is over.
     */
    private void win() {

        Intent tmp = new Intent(this, ACGameOverActivity.class);
        startActivity(tmp);
        finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        controller.stopRunning();
        controller.deleteObserver(this);
        finish();
    }

    @Override
    public void update(Observable o, Object arg) {
        win();
    }

}
