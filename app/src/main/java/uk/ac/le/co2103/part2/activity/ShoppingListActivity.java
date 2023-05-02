package uk.ac.le.co2103.part2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import uk.ac.le.co2103.part2.R;
import uk.ac.le.co2103.part2.adapter.ProductListAdapter;
import uk.ac.le.co2103.part2.database.ShoppingListDao;
import uk.ac.le.co2103.part2.database.ShoppingListDatabase;
import uk.ac.le.co2103.part2.listener.OnItemClickListener;
import uk.ac.le.co2103.part2.model.Product;
import uk.ac.le.co2103.part2.model.ShoppingList;


public class ShoppingListActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView TV_SL_name;
    int slId;
    ShoppingList sl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        RecyclerView rvProductList = findViewById(R.id.rvShoppingList);

        ShoppingListDatabase db = ShoppingListDatabase.getInstance(this);
        ShoppingListDao dao = db.shoppingListDao();
        slId = getIntent().getIntExtra("SL_ID", 0);
        sl = dao.loadById(slId);
        Log.d(TAG+"sla", sl.toString());


        TV_SL_name = findViewById(R.id.TV_SL_name);
        TV_SL_name.setText(sl.name);



        //Set the adapter and layout manager for the RecyclerView
        ProductListAdapter mAdapter = new ProductListAdapter(sl, this);
        rvProductList.setAdapter(mAdapter);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));
    }

    // fab button
    public void onClickFab(View v) {
        Intent intent = new Intent(ShoppingListActivity.this, AddProductActivity.class);
        intent.putExtra("SL_ID",sl.getListId());
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_box_edit_or_delete);
        TextView titleView = dialog.findViewById(R.id.P_title);
        titleView.setText("Delete Product?");
        TextView messageView = dialog.findViewById(R.id.P_message);
        messageView.setText("Are you Sure you want to delete this Product");

        ShoppingListDao dao = ShoppingListDatabase.getInstance(this).shoppingListDao();
        Button buttonDelete = dialog.findViewById(R.id.B_PB_delete);
        buttonDelete.setOnClickListener(v -> {
            List<Product> productList = sl.getProducts();
            productList.remove(position);
            sl.setProducts(productList);
            dao.update(sl);
            dialog.dismiss();

            recreate();
        });
        Button buttonEdit = dialog.findViewById(R.id.B_PB_edit);
        buttonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingListActivity.this, UpdateProductActivity.class);
            intent.putExtra("SL_ID", sl.getListId());
            intent.putExtra("P_ID", sl.getProducts().get(position).name);
            startActivity(intent);
        });
        dialog.show();
    }
}