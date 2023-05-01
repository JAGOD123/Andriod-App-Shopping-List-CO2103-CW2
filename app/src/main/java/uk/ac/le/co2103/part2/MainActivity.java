package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import uk.ac.le.co2103.part2.model.ShoppingList;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String LIST = "LIST";
    ShoppingListAdapter.ViewHolder shoppingListAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);

        ShoppingListDatabase db = ShoppingListDatabase.getInstance(this);
        ShoppingListDao dao = db.shoppingListDao();

        //
        //dao.nukeTable();


        Intent intent_in = getIntent();
        Bundle b = intent_in.getExtras();
        if (b!=null){
            ShoppingList sl = (ShoppingList) b.getSerializable("NEW_SHOPPING_LIST");
            dao.insert(sl);

        }


        RecyclerView rvShoppingList = (RecyclerView) findViewById(R.id.recyclerview);
        ShoppingListAdapter adapter = new ShoppingListAdapter(dao, dao.getAll());
        rvShoppingList.setAdapter(adapter);
        rvShoppingList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CreateListActivity.class);
        startActivity(intent);
    }

}