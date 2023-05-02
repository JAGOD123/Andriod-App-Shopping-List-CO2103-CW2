package uk.ac.le.co2103.part2.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.le.co2103.part2.listener.OnItemClickListener;
import uk.ac.le.co2103.part2.listener.OnItemLongClickListener;
import uk.ac.le.co2103.part2.R;
import uk.ac.le.co2103.part2.database.ShoppingListDao;
import uk.ac.le.co2103.part2.model.ShoppingList;



public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    List<ShoppingList> data;
    Context context;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;
    private ShoppingListDao shoppingListDao;


    public ShoppingListAdapter(List<ShoppingList> data, OnItemLongClickListener longClickListener,
                               OnItemClickListener clickListener) {
        this.data = data;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ShoppingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);

        // Click listener melarky
        ShoppingListAdapter.ViewHolder viewHolder = new ShoppingListAdapter.ViewHolder(view);

        viewHolder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                int position = viewHolder.getAdapterPosition();
                longClickListener.onItemLongClick(position);
                return true;
            }
            return false;
        });
        viewHolder.itemView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            clickListener.onItemClick(position);
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

    /*
    public void deleteShoppingList(ShoppingListDao dao, int position) {
        ShoppingList shoppingList = dao.getAll().get(position);
        //dao.delete(shoppingList);
        dao.deleteProductsInList(shoppingList.getProducts());
        dao.getAll().remove(position);
        notifyItemRemoved(position);
    }

     */

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.TV_P_name);
            image = view.findViewById(R.id.IV_list);
        }


    }
}
