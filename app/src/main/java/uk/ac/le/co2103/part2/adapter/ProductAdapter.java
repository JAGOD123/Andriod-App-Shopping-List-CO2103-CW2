package uk.ac.le.co2103.part2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.le.co2103.part2.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_product, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Spinner mSpinner;

        public ViewHolder(@NonNull View v){
            super(v);
            mSpinner = v.findViewById(R.id.spinner);
        }
    }
}
