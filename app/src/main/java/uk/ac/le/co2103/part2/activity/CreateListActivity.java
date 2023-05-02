package uk.ac.le.co2103.part2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uk.ac.le.co2103.part2.R;
import uk.ac.le.co2103.part2.database.ShoppingListDao;
import uk.ac.le.co2103.part2.database.ShoppingListDatabase;
import uk.ac.le.co2103.part2.model.Product;
import uk.ac.le.co2103.part2.model.ShoppingList;

public class CreateListActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView IVPreviewImage;
    ShoppingList newSL;
    //TODO Cant open new ShoppingList
    ShoppingListDatabase db = ShoppingListDatabase.getInstance(this);
    ShoppingListDao dao = db.shoppingListDao();

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
        Log.d(TAG, listName.getText().toString());
        if (!listName.getText().toString().equals("")){
            newSL.setName(listName.getText().toString());
            newSL.setProducts(new ArrayList<>());
            intent.putExtra("NEW_SHOPPING_LIST", newSL);
            startActivity(intent);
            dao.insert(newSL);
        } else {
            Toast toast = Toast.makeText(this, "Needs a List Name", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    int PICK_IMAGE = 200;
    public void OnClickImage(View v){
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(in, "Select Picture"), PICK_IMAGE);
    }

    // Image from gallery
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == PICK_IMAGE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the bitmap from the Uri using the MediaStore API
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    String savePath = selectedImageUri.toString().substring(
                            selectedImageUri.toString().indexOf("primary") + "primary".length() + 1);
                    // Create a new file within your app's internal storage directory
                    FileOutputStream outputStream;
                    File file = new File(getFilesDir(), savePath);
                    // Use a FileOutputStream to write the image data to the file
                    try {
                        outputStream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                    String fullSavePath = getFilesDir().getPath() + "/" + savePath;
                    newSL.setImage(fullSavePath);
                }
            }
        }
    }


}