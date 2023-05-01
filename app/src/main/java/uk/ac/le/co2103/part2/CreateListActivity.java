package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CreateListActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView IVPreviewImage;
    ShoppingList newSL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        newSL = new ShoppingList();
    }

    public void onClickSave(View v) {
        Intent intent = new Intent(CreateListActivity.this, MainActivity.class);
        EditText listName = (EditText) v.getRootView().findViewById(R.id.inputNameBox);
        if (listName.getText().toString() != null){
            newSL.setName(listName.getText().toString());
            Log.d("System.out", newSL.getName());
            intent.putExtra("NEW_SHOPPING_LIST", newSL);
            startActivity(intent);
        }


    }

    int PICK_IMAGE = 200;
    public void OnClickImage(View v){
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(in, "Select Picture"), PICK_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == PICK_IMAGE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    /*
                    // Get the bitmap from the Uri using the MediaStore API
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // Create a new file within your app's internal storage directory
                    FileOutputStream outputStream;
                    File file = new File(getFilesDir(), "my_image.jpg");
                    Log.d("tag", String.valueOf(getFilesDir()));
                    // Use a FileOutputStream to write the image data to the file
                    try {
                        outputStream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    */


                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                    Log.d(TAG, selectedImageUri.toString());
                    newSL.setImage(selectedImageUri.toString());
                }
            }
        }
    }


}