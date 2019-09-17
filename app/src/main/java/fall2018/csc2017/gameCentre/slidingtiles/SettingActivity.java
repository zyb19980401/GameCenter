package fall2018.csc2017.gameCentre.slidingtiles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import fall2018.csc2017.gameCentre.R;

/**
 * The activity for setting.
 */
public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Selected complexity by user.
     */
    static int selectedComplexity = 4;

    /**
     * String array to fill into complexity spinner.
     */
    String[] complexity = {"4*4", "3*3", "5*5"};

    /**
     * String array to fill into complexity spinner.
     */
    Integer[] undoSteps = {3, 2, 1, 4, 5};

    /**
     * ImageView to display image selected by user.
     */
    private ImageView imageView;

    /**
     * Indicator for getting photo.
     */
    private static final int PHOTO_OK = 0;

    /**
     * Image selected by user.
     */
    public static Bitmap selectedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        //setFinalScore spinner and adapter for complexity.
        Spinner complexitySpinner = findViewById(R.id.complexitySpinner);
        ArrayAdapter<String> complexityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, complexity);
        setAdapter(complexitySpinner, complexityAdapter);
        //setFinalScore spinner and adapter for undo steps.
        Spinner undoStepsSpinner = findViewById(R.id.undoStepsSpinner);
        ArrayAdapter<Integer> undoStepsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, undoSteps);
        setAdapter(undoStepsSpinner, undoStepsAdapter);
        addUploadButtonListener();
        addDeleteButtonListener();
        imageView = findViewById(R.id.imageView);
        if (selectedImage != null) {
            imageView.setImageBitmap(selectedImage);
        }


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
            case R.id.complexitySpinner:
                String selection1 = arg0.getItemAtPosition(position).toString();
                selectedComplexity = Integer.parseInt(selection1.substring(0, 1));
                if (BoardManager.SelfDefinedIndicator) {
                    SelfDefinedSTTile.split(selectedImage, selectedComplexity, selectedComplexity);
                }
                break;
            case R.id.undoStepsSpinner:
                BoardManager.numSteps = (int) arg0.getItemAtPosition(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    /**
     * Activate the Upload button.
     * citation:
     * upload image from gallery
     * this is according to a post from stack overflow
     * https://stackoverflow.com/questions/9107900/how-to-upload-image-from-gallery-in-android
     */
    private void addUploadButtonListener() {
        Button uploadButton = findViewById(R.id.UploadButton);
        uploadButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, PHOTO_OK);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imageView = findViewById(R.id.imageView);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_OK && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                if (imageUri != null) {
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImage = BitmapFactory.decodeStream(imageStream);
                    imageView.setImageBitmap(selectedImage);
                    BoardManager.SelfDefinedIndicator = true;
                    SelfDefinedSTTile.split(selectedImage, selectedComplexity, selectedComplexity);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Activate the Delete button.
     */
    private void addDeleteButtonListener() {
        Button deleteButton = findViewById(R.id.deletebutton);
        deleteButton.setOnClickListener(v -> {
            BoardManager.SelfDefinedIndicator = false;
            selectedImage = null;
            imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(selectedImage);
        });
    }


}


