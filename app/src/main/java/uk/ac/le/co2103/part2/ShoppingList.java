package uk.ac.le.co2103.part2;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "shopping_lists")
public class ShoppingList implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int listId;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "image")
    public String image;


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

    @Override
    public String toString() {
        return "ShoppingList{" +
                "listId='" + listId + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
