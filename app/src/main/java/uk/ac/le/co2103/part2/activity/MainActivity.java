package uk.ac.le.co2103.part2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import uk.ac.le.co2103.part2.R;
import uk.ac.le.co2103.part2.adapter.ShoppingListAdapter;
import uk.ac.le.co2103.part2.database.ShoppingListDao;
import uk.ac.le.co2103.part2.database.ShoppingListDatabase;
import uk.ac.le.co2103.part2.listener.OnItemClickListener;
import uk.ac.le.co2103.part2.listener.OnItemLongClickListener;

public class MainActivity extends AppCompatActivity implements OnItemLongClickListener, OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String LIST = "LIST";



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_main);
        ShoppingListDao dao = ShoppingListDatabase.getInstance(this).shoppingListDao();

        Log.d(LIST, dao.getAll().toString());

        //
        //dao.nukeTable();

        RecyclerView rvShoppingList = (RecyclerView) findViewById(R.id.recyclerview);
        ShoppingListAdapter adapter = new ShoppingListAdapter(dao.getAll(), this, this);
        rvShoppingList.setAdapter(adapter);
        rvShoppingList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CreateListActivity.class);
        startActivity(intent);
    }

    // Long Click
    @SuppressLint("SetTextI18n")
    @Override
    public void onItemLongClick(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_box_layout);
        TextView titleView = dialog.findViewById(R.id.popup_box_title);
        titleView.setText("Delete ShoppingList?");
        TextView messageView = dialog.findViewById(R.id.popup_box_message);
        messageView.setText("Are you Sure you want to delete this Shopping List");
        ShoppingListDao dao = ShoppingListDatabase.getInstance(this).shoppingListDao();
        Button button = dialog.findViewById(R.id.popup_box_button);
        button.setOnClickListener(v -> {

            dao.delete(dao.getAll().get(position));
            dialog.dismiss();

            recreate();
        });
        dialog.show();
    }

    // Short Click
    @Override
    public void onItemClick(int position) {
        ShoppingListDao dao = ShoppingListDatabase.getInstance(this).shoppingListDao();
        //Toast toast = Toast.makeText(this, dao.getAll().get(position).name, Toast.LENGTH_SHORT);
        //toast.show();
        Intent intent = new Intent(MainActivity.this, ShoppingListActivity.class);
        Log.d(TAG, "onItemClick:" + dao.getAll().get(position).getListId());
        intent.putExtra("SL_ID", dao.getAll().get(position).getListId());
        startActivity(intent);
    }
}