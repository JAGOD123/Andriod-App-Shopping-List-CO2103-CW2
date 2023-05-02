package uk.ac.le.co2103.part2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "shopping_lists")
public class ShoppingList implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int listId;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "productListId")
    public List<Product> products;


    public ShoppingList() {}

    public ShoppingList(String name, String image) {
        this.name = name;
        this.image = image; // will remove this as wont construct without one
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "listId=" + listId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", products=" + products +
                '}';
    }
}
