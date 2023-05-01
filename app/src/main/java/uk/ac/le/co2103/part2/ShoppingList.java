package uk.ac.le.co2103.part2;

import android.net.Uri;

import java.io.Serializable;

public class ShoppingList implements Serializable {

    private String listId;
    public String name;
    public String image;


    public ShoppingList() {}

    public ShoppingList(String name, String image) {
        this.name = name;
        this.image = image; // will remove this as wont construct without one
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
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
