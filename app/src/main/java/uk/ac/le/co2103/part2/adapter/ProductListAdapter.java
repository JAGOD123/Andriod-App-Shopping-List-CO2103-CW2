package uk.ac.le.co2103.part2.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.le.co2103.part2.R;
import uk.ac.le.co2103.part2.model.ShoppingList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private ShoppingList sl;


    public ProductListAdapter(ShoppingList sl) {
        this.sl = sl;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductListAdapter.ViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.recyclerview_item_product,
                null));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        //holder.mTitleTextView.setText(sl.name);
        holder.mNameTextView.setText(sl.products.get(position).name);
        holder.mQuantityTextView.setText(Integer.toString(sl.products.get(position).quantity));
        holder.mUnitTextView.setText(sl.products.get(position).unit);
    }

    @Override
    public int getItemCount() {
        return sl.products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;
        TextView mQuantityTextView;
        TextView mUnitTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.TV_P_name);
            mQuantityTextView = itemView.findViewById(R.id.TV_P_quantity);
            mUnitTextView = itemView.findViewById(R.id.TV_P_unit);
        }
        /*

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Product product = mProducts.get(position);
            String description = product.getName() + " (" + product.getQuantity() + " " + product.getUnit() + ")";
            Toast.makeText(mContext, description, Toast.LENGTH_SHORT).show();
        }

         */
    }
}