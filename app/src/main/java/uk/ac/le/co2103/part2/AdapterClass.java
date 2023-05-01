package uk.ac.le.co2103.part2;

import android.content.Context;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*
public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {
    private List<ShoppingList> memberShoppingList;

    public ShoppingListAdapter(List<ShoppingList> shoppingLists){
        memberShoppingList = shoppingLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View mainActivityView = inflater.inflate(R.layout.activity_main, parent, false); //might be wrong

        // Return a new holder instance
        return new ViewHolder(mainActivityView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingList shoppingList = memberShoppingList.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(shoppingList.name);
        ImageView imageView = holder.imageImageView;
        imageView.setImageURI();



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public ImageView imageImageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.TV_name);
            imageImageView = (ImageView) itemView.findViewById(R.id.IVPreviewImage);

        }

    }
}
*/



public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {
    ArrayList<ShoppingList> data = new ArrayList<>();

    public AdapterClass(ArrayList<ShoppingList> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.ViewHolder holder, int position) {
        holder.name.setText(data.get(position).name);
        Uri uri = Uri.parse(data.get(position).image);
        //TODO Set Image URI to the file in storage making sure there is a unique name for each file
        //  Probably just go with the URI from the gallery

        Log.d("HERE"+position,uri.toString());
        holder.image.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.TV_name);
            image = view.findViewById(R.id.IV_list);
        }
    }
}
