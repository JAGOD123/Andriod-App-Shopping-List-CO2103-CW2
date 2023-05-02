package uk.ac.le.co2103.part2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uk.ac.le.co2103.part2.R;
import uk.ac.le.co2103.part2.database.ShoppingListDao;
import uk.ac.le.co2103.part2.database.ShoppingListDatabase;
import uk.ac.le.co2103.part2.model.Product;
import uk.ac.le.co2103.part2.model.ShoppingList;

public class AddProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Serializable {

    private static final String TAG = MainActivity.class.getSimpleName();
    Product newProduct;
    String[] choiceUnit = { "Unit", "KG", "Liter"};
    int slId;
    ShoppingList sl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, choiceUnit);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        ShoppingListDao dao = ShoppingListDatabase.getInstance(this).shoppingListDao();

        slId = getIntent().getIntExtra("SL_ID", -1);
        sl = dao.loadById(slId);
        Log.d(TAG + " loadDAO", sl.toString());



        newProduct = new Product();
    }

    public void onClickSave(View v) {
        ShoppingListDao dao = ShoppingListDatabase.getInstance(this).shoppingListDao();
        Intent intent = new Intent(AddProductActivity.this, ShoppingListActivity.class);
        EditText productName = (EditText) v.getRootView().findViewById(R.id.editTextName);
        EditText productQuantity = (EditText) v.getRootView().findViewById(R.id.editTextQuantity);

        Log.d(TAG, "onClickSave:"+ productQuantity.getText().toString());
        if (productName.getText().toString().equals("")) {
            Toast toast = Toast.makeText(this, "Needs a Product Name", Toast.LENGTH_SHORT);
            toast.show();
        } else if (productQuantity.getText().toString().equals("")) {
            Toast toast = Toast.makeText(this, "Needs a Quantity", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            newProduct.setName(productName.getText().toString());
            newProduct.setQuantity(Integer.parseInt(productQuantity.getText().toString()));


            if(sl.productInList(newProduct)){
                Toast toast = Toast.makeText(this, "Product already exists", Toast.LENGTH_SHORT);
                toast.show();
            } else{
                List<Product> productList = sl.getProducts();
                productList.add(newProduct);
                sl.setProducts((List<Product>)productList);

                //Log.d(TAG, sl.products.toString());
                //Log.d(TAG, sl.toString());


                dao.update(sl);

                intent.putExtra("SL_ID", sl.getListId());
                startActivity(intent);
            }


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        newProduct.setUnit(choiceUnit[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
