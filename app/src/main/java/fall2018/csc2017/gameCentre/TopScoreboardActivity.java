package fall2018.csc2017.gameCentre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * the Activity which is the top10 Scoreboard
 */

public class TopScoreboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topscoreboard);

        SetTop10();
    }

    /**
     * To set the highest 10 Scores
     */
    public void SetTop10() {
        ScoreManager manager = ScoreManager.getScoreManager(this);
        manager.loadFromTopFile();
        manager.loadFromUserFile();
        ArrayList text = manager.getTopMap().get(GameCentreActivity.current);
        // 得到当前的Game Type 的 arraylist of score
        ArrayList<TextView> textViewArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = "top" + i;
            int id = getResources().getIdentifier(name, "id", getPackageName());
            if (id != 0) {
                TextView textView = findViewById(id);
                textViewArrayList.add(textView);
            }
        }

        // 得到当前的Game Type 的 arraylist of score(只要不是null, 就放进去。)

        if (!(text == null)) {
            for (int counter = 0; counter <= text.size() - 1; counter++) {
                TextView textView = textViewArrayList.get(text.size() - counter - 1);
                if (text.get(counter) != null) {
                    String string = text.get(counter).toString();
                    textView.setText(string);
                }
            }
        }


    }
}

