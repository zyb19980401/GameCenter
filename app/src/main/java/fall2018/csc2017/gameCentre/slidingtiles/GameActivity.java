


package fall2018.csc2017.gameCentre.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import fall2018.csc2017.gameCentre.R;


/**
 * The game activity.
 */
public class GameActivity extends AppCompatActivity implements Observer {


    /**
     * the text view of time left.
     */
    private TextView mTextViewCountDown;

    /**
     * the text view of steps.
     */
    private TextView textView;

    /**
     * the controller of this activity.
     */
    private GameActivityController controller;

    /**
     * A list of Buttons.
     */
    private ArrayList<Button> tileButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new GameActivityController(this);

        setContentView(R.layout.activity_main);
        createTileButtons();
        GestureDetectGridView gridView = findViewById(R.id.grid);
        setGridView(gridView, tileButtons);
        Button undoButton = findViewById(R.id.undoButton);
        addUndoButtonListener(undoButton);
        controller.addObserver(this);
        mTextViewCountDown = findViewById(R.id.textView4);
        controller.createTimer();
        textView = findViewById(R.id.steps);

    }

    /**
     * Set grid view.
     */
    void setGridView(GestureDetectGridView gridView, ArrayList<Button> tileButtons) {
        gridView.setNumColumns(controller.getComplexity());
        gridView.setSTBoardManager(controller.getBoardManager());
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        int columnWidth = displayWidth / controller.getComplexity();
                        int columnHeight = displayHeight / controller.getComplexity();
                        //display(gridView);
                        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
                    }
                });
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void update(Observable o, Object arg) {
        controller.save();
        updateTileButtons();
        textView.setText(controller.getStep());
        mTextViewCountDown.setText(controller.getTimeLeftFormatted());
        //display();
        if (controller.checkWin()) {
            Intent tmp = new Intent(this, WinnerActivity.class);
            startActivity(tmp);
            controller.deleteObserver(this);
            finish();
        }
        ;
    }

    /**
     * Add UndoButton Listener.
     *
     * @param undoButton the Button of undo
     */
    void addUndoButtonListener(Button undoButton) {
        undoButton.setOnClickListener(v -> {
            controller.undo();
        });
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    void updateTileButtons() {
        //saver.saveToFile(boardManager, session.getSTSaveFile());
        Board board = controller.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / board.getComplexity();
            int col = nextPos % board.getComplexity();
            setBackground(board, b, row, col);
            nextPos++;
        }
    }

    /**
     * Set background with Tiles.
     *
     * @param board the board;
     *              button: the button;
     *              row: row number;
     *              col: column number
     */
    private void setBackground(Board board, Button button, int row, int col) {
        if (!BoardManager.SelfDefinedIndicator) {
            button.setBackgroundResource(board.getTile(row, col).getBackground());
        } else {
            // set game background to self-defined image.
            button.setBackground(((SelfDefinedSTTile) board.getTile(row, col)).getDrawableBackground(this));
        }
    }

    /**
     * Create the buttons for displaying the tiles.
     */
    void createTileButtons() {
        // if (boardManager != null) {
        Board board = controller.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getComplexity(); row++) {
            for (int col = 0; col != board.getComplexity(); col++) {
                Button tmp = new Button(this);
                setBackground(board, tmp, row, col);
                tileButtons.add(tmp);
            }
        }
    }


    /**
     * Go back to starting activity when press back.
     */
    @Override
    public void onBackPressed() {
        controller.deleteObserver(this);
        controller.getBoardManager().getBoard().deleteObserver(controller);
        Intent intent = new Intent(this, StartingActivity.class);
        startActivity(intent);
    }
}
