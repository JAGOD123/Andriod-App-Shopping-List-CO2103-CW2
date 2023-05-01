package uk.ac.le.co2103.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

//TODO need to make you child of main activity and add an 'up' button
public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        ShoppingListDatabase db = ShoppingListDatabase.getInstance(this);
        ShoppingListDao dao = db.shoppingListDao();

        RecyclerView rvProductList = findViewById(R.id.rvShoppingList);

        ProductListAdapter mAdapter = new ProductListAdapter(this);

        // Set the adapter and layout manager for the RecyclerView
        rvProductList.setAdapter(mAdapter);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));
    }
}