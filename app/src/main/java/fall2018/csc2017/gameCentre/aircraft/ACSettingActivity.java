package fall2018.csc2017.gameCentre.aircraft;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import fall2018.csc2017.gameCentre.GameCentreActivity;
import fall2018.csc2017.gameCentre.R;

/**
 * The AirCraft Setting class.
 */
public class ACSettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * different types of bullets can be chosen.
     */
    private int[] imageIcon = {R.mipmap.bullet1, R.mipmap.bullet2};

    /**
     * different difficulties can be chosen.
     */
    private String[] difficulty = {"Easy", "Hard"};


    /**
     * The Difficulty selected.
     */
    private static String selectedDifficulty = "Easy";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aircraft_setting);
        //set spinner and adapter for complexity.
        Spinner bulletStyleSpinner = findViewById(R.id.bulletStyleSpinner);
        bulletStyleSpinner.setOnItemSelectedListener(this);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), imageIcon);
        bulletStyleSpinner.setAdapter(customAdapter);

        Spinner difficultySpinner = findViewById(R.id.difficultySpinner);
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficulty);
        setAdapter(difficultySpinner, difficultyAdapter);

    }

    /**
     * @return return difficulty chosen.
     */
    public static String getSelectedDifficulty() {
        return selectedDifficulty;
    }


    /**
     * @param spinner a spinner to allow user to select a complexity from the given list.
     *                citation:
     *                this is adapted from a post of tutorialspoint
     *                https://www.tutorialspoint.com/android/android_spinner_control.htm
     * @param adapter an adapter to fill list to spinner.
     * @param <T>     generic type of elements of ArrayAdapter.
     */
    private <T> void setAdapter(Spinner spinner, ArrayAdapter<T> adapter) {
        spinner.setOnItemSelectedListener(this);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public class CustomAdapter extends BaseAdapter {
        Context context;
        int imageIcon[];
        LayoutInflater inflater;

        CustomAdapter(Context applicationContext, int[] imageIcon) {
            this.context = applicationContext;
            this.imageIcon = imageIcon;
            inflater = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return imageIcon.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) view = inflater.inflate(R.layout.custom_spinner_items, null);
            ImageView icon = view.findViewById(R.id.imageView);
            icon.setImageResource(imageIcon[i]);
            return view;
        }
    }


    //Performing action onItemSelected and onNothing selected.

    /**
     * implement a switch selection in the onItemSelected method for spinner
     * citation:
     * this is according to the second and third answers from a forum of stack overflow on May 28,2015
     * https://stackoverflow.com/questions/20151414/how-can-i-use-onitemselected-in-android
     */

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        switch (arg0.getId()) {
            case R.id.bulletStyleSpinner:
                GameView.setBulletStyle(imageIcon[position]);
                break;
            case R.id.difficultySpinner:
                selectedDifficulty = arg0.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent temp = new Intent(this, ACStartingActivity.class);
        startActivity(temp);

    }


}



