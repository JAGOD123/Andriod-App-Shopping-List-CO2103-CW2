package uk.ac.le.co2103.part2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.le.co2103.part2.model.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Product> mProducts;

    public ProductListAdapter(Context context) {
        mProducts = new ArrayList<>();
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_product, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        holder.mNameTextView.setText(mProducts.get(position).name);
        holder.mQuantityTextView.setText(mProducts.get(position).quantity);
        holder.mUnitTextView.setText(mProducts.get(position).unit);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;
        TextView mQuantityTextView;
        TextView mUnitTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.TV_name);
            mQuantityTextView = itemView.findViewById(R.id.TV_quantity);
            mUnitTextView = itemView.findViewById(R.id.TV_unit);
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