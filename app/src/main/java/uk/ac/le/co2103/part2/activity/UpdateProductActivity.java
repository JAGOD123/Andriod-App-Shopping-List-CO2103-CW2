package uk.ac.le.co2103.part2.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import uk.ac.le.co2103.part2.R;
import uk.ac.le.co2103.part2.database.ShoppingListDao;
import uk.ac.le.co2103.part2.database.ShoppingListDatabase;
import uk.ac.le.co2103.part2.model.Product;
import uk.ac.le.co2103.part2.model.ShoppingList;

public class UpdateProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    String[] choiceUnit = { "Unit", "KG", "Liter"};
    int slId;
    String pID;
    ShoppingList sl;
    Product product;
    Product orgProduct;

    TextView TV_productName;
    TextView TV_productQuantity;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        ShoppingListDao dao = ShoppingListDatabase.getInstance(this).shoppingListDao();
        slId = getIntent().getIntExtra("SL_ID", 0);
        sl = dao.loadById(slId);

        pID = getIntent().getStringExtra("P_ID");
        product = sl.findProductWithName(pID);
        orgProduct = sl.findProductWithName(pID);


        Spinner spinner = (Spinner) findViewById(R.id.S_UPDATE_PRODUCT_units);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, choiceUnit);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setSelection(Arrays.asList(choiceUnit).indexOf(product.unit));

        TV_productName = findViewById(R.id.ET_UPDATE_PRODUCT_productName);
        TV_productName.setText(product.name);

        TV_productQuantity = findViewById(R.id.ET_UPDATE_PRODUCT_productQuantity);
        TV_productQuantity.setText(Integer.toString(product.quantity));

    }

    public void onClickAdd(View v){
        TV_productQuantity = findViewById(R.id.ET_UPDATE_PRODUCT_productQuantity);
        int quantity = product.getQuantity();
        quantity += 1;
        product.setQuantity(quantity);
        TV_productQuantity.setText(Integer.toString(product.quantity));
    }

    public void onClickMinus(View v){
        int quantity = product.getQuantity();
        if(product.quantity > 1){
            TV_productQuantity = findViewById(R.id.ET_UPDATE_PRODUCT_productQuantity);
            quantity -= 1;
            product.setQuantity(quantity);
            TV_productQuantity.setText(Integer.toString(product.quantity));
        }else{
            Toast.makeText(this, "Quantity must be positive", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickSave(View c){
        Intent intent = new Intent(UpdateProductActivity.this, ShoppingListActivity.class);
        List<Product> productsList = sl.getProducts();

        productsList.set(productsList.indexOf(orgProduct), product);
        sl.setProducts(productsList);
        ShoppingListDao dao = ShoppingListDatabase.getInstance(this).shoppingListDao();
        dao.update(sl);
        intent.putExtra("SL_ID", sl.getListId());
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        product.setUnit(choiceUnit[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
