package uk.ac.le.co2103.part2;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ShoppingListViewModel extends ViewModel {
    private ArrayList<ShoppingList> data = new ArrayList<>();

    public ArrayList<ShoppingList> getData() {
        return data;
    }

    public void addData(ShoppingList sl) {
        data.add(sl);
    }
}
