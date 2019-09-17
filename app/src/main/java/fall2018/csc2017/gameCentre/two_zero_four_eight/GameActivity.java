package fall2018.csc2017.gameCentre.two_zero_four_eight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.gameCentre.R;

/**
 * The 2048 game activity.
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener, Observer {
    /**
     * The TextView depicts current score.
     */
    private TextView tvScore;
    /**
     * The button for undo action.
     */
    private Button twoUndoButton;
    /**
     * The game activity controller.
     */
    private GameActivityController controller;
    /**
     * The GridView for the board.
     */
    GridView tableGridView;
    /**
     * The game view adapter.
     */
    private TableGridviewAdapter adapter;
    /**
     * The buttons for actions.
     */
    Button leftButton;
    Button rightButton;
    Button upButton;
    Button downButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoactivity_main);
        Button twoRestartButton = findViewById(R.id.twoRestartButton);
        twoUndoButton = findViewById(R.id.twoUndoButton);
        tvScore = findViewById(R.id.tvScore);

        // the buttons to control actions. We didn't use animation.
        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);
        upButton = findViewById(R.id.upButton);
        downButton = findViewById(R.id.downButton);

        twoRestartButton.setOnClickListener(this);
        twoUndoButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
        upButton.setOnClickListener(this);
        downButton.setOnClickListener(this);

        controller = new GameActivityController(this);
        controller.addObserver(this);
        setButtonsParams();
        controller.startGame();
        tvScore.setText(String.valueOf(controller.getBMScore()));
        twoUndoButton.setEnabled(controller.enableUndo());
        tableGridView = findViewById(R.id.tablegridview);
        setAdapter();
    }

    @Override
    public void onClick(View v) {
        // Actions
        switch (v.getId()) {
            case R.id.leftButton:
                controller.action("left");
                break;
            case R.id.rightButton:
                controller.action("right");
                break;
            case R.id.upButton:
                controller.action("up");
                break;
            case R.id.downButton:
                controller.action("down");
                break;
            case R.id.twoRestartButton:
                controller.setNewGame(true);
                controller.startGame();
                controller.setBMRestart();
                break;
            case R.id.twoUndoButton:
                controller.getTwoBoardManager().resetStepsAfterUndo();
                controller.setBMUsedUndo();
                controller.BMUndo();
                break;
        }
        controller.execution();
        tvScore.setText(String.valueOf(controller.getBMScore()));
        twoUndoButton.setEnabled(controller.enableUndo());
        setAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, StartingActivity.class);
        startActivity(intent);
    }

    @Override
    public void update(Observable o, Object arg) {
        tvScore.setText(String.valueOf(controller.getBMScore()));
        twoUndoButton.setEnabled(controller.enableUndo());
        if (controller.isGameOver()) {
            controller.deleteObserver(this);
        }
    }

    /**
     * Setter for the adapter.
     */
    void setAdapter() {
        adapter = new TableGridviewAdapter(this, controller.getTwoBoardManager().getTwoBoard());
        tableGridView.setAdapter(adapter);
        tableGridView.setScrollContainer(false);
    }

    /*
     Dynamically adjust action buttons

     Adapted from:
     https://stackoverflow.com/questions/18568289/how-to-set-gravity-in-programmatically-in-a-relativelayout
     */

    /**
     * Setting the layout parameters, adjusting the action buttons dynamically.
     */
    void setButtonsParams() {
        RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        llp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        llp.addRule(RelativeLayout.CENTER_VERTICAL);
        leftButton.setLayoutParams(llp);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlp.addRule(RelativeLayout.CENTER_VERTICAL);
        rightButton.setLayoutParams(rlp);

        RelativeLayout.LayoutParams ulp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ulp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        ulp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        upButton.setLayoutParams(ulp);

        RelativeLayout.LayoutParams dlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        downButton.setLayoutParams(dlp);
    }
}