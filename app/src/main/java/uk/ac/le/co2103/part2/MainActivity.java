package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView mRecyclerView;
    private ShoppingListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);

        viewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);


        Log.d(TAG, "onCreate()");
        Log.d(TAG + "1", viewModel.getData().toString());


        //TextView Textv = (TextView) findViewById(R.id.textView2);
        //ImageView Imagev = (ImageView) findViewById(R.id.IV_SL_ICON);


        // Response from CreateListActivity
        Intent intent_in = getIntent();
        Bundle b = intent_in.getExtras();
        if (b!=null){
            Log.e(TAG, "H1");
            Log.d(TAG+" 2", viewModel.getData().toString());
            ShoppingList sl = (ShoppingList) b.getSerializable("NEW_SHOPPING_LIST");
            viewModel.addData(sl);
            Log.d(TAG+" 3", viewModel.getData().toString());
        }


        AdapterClass adapter = new AdapterClass(viewModel.getData());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }


    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CreateListActivity.class);
        startActivity(intent);
    }

}