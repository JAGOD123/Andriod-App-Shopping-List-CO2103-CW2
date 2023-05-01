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
    private OnItemLongClickListener longClickListener;
    private ShoppingListDao shoppingListDao;


    public ShoppingListAdapter(List<ShoppingList> data, OnItemLongClickListener longClickListener) {
        this.data = data;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        ShoppingListAdapter.ViewHolder viewHolder = new ShoppingListAdapter.ViewHolder(view);

        viewHolder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                int position = viewHolder.getAdapterPosition();
                longClickListener.onItemLongClick(position);
                return true;
            }
            return false;
        });
        return viewHolder;
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

    public void deleteShoppingList(ShoppingListDao dao, int position) {
        ShoppingList shoppingList = dao.getAll().get(position);
        //dao.delete(shoppingList);
        dao.deleteProductsInList(shoppingList.getProducts());
        dao.getAll().remove(position);
        notifyItemRemoved(position);
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


    }
}
