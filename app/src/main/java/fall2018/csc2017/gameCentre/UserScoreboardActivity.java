package fall2018.csc2017.gameCentre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;


import java.util.ArrayList;


/**
 * a Activity for UserScoreboard, which is only for one user
 */
public class UserScoreboardActivity extends AppCompatActivity {
    UserScoreBoardActivityController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userscoreboard);
        SetByCurrentUser();
    }

    /**
     * Set the current user to the Scoreboard.
     */
    @SuppressWarnings("unchecked")
    public void SetByCurrentUser() {
        ScoreManager manager = ScoreManager.getScoreManager(this);
        manager.loadFromTopFile();
        manager.loadFromUserFile();
        // arraylist of scoretuples 对于一个User
        ArrayList text1 = manager.getUserMap().get(Session.getSession().getName());
        System.out.println(text1);
        controller = new UserScoreBoardActivityController(GameCentreActivity.getCurrent(), text1);
        // helper 得出当前GameType的 所有Scoretuple (soreted)
        ArrayList text = controller.helper();
        System.out.println(text);
        ArrayList<TextView> textViewArrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String name = "top" + i;
            int id = getResources().getIdentifier(name, "id", getPackageName());
            if (id != 0) {
                TextView textView = findViewById(id);
                textViewArrayList.add(textView);
            }
        }


        // 总共有10个， 如果不到10 个 把这个size改成当前的size


        if (!(text == null) && !text.isEmpty()) {
            int size;
            if (text.size() < 10) {
                size = text.size();
            } else {
                size = 10;
            }
            for (int counter = 0; counter <= size - 1; counter++) {
                TextView textView = textViewArrayList.get(size - counter - 1);
                if (text.get(counter) != null) {
                    String string = text.get(counter).toString();
                    textView.setText(string);
                }
            }
        }


    }

}

