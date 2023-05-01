package uk.ac.le.co2103.part2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.le.co2103.part2.model.ShoppingList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    List<ShoppingList> data;
    Context context;
    private ShoppingListDao shoppingListDao;


    public ShoppingListAdapter(ShoppingListDao shoppingListDao, List<ShoppingList> data) {
        this.data = data;
        this.shoppingListDao = shoppingListDao;
    }

    @NonNull
    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListAdapter.ViewHolder holder, int position) {
        holder.name.setText(data.get(position).name);
        if (data.get(position).image != null){
            Uri uri = Uri.parse(data.get(position).image);
            holder.image.setImageURI(uri);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder{
        boolean onLongClick;
        TextView name;
        ImageView image;
        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.TV_name);
            image = view.findViewById(R.id.IV_list);
        }

        /*
        public void deleteItem(int position) {
            ShoppingList shoppingList = data.get(position);
            shoppingListDao.delete(shoppingList);
            shoppingListDao.deleteProductsInList(shoppingList.getProducts());
            data.remove(position);
            notifyItemRemoved(position);
        }
        */

    }
}
