package uk.ac.le.co2103.part2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uk.ac.le.co2103.part2.model.ShoppingList;

@Dao
public interface ShoppingListDao {
    @Query("SELECT * FROM shopping_lists ORDER BY listId DESC")
    List<ShoppingList> getAll();

    @Query("SELECT * FROM shopping_lists WHERE listId IN (:listIds)")
    List<ShoppingList> loadAllByIds(int[] listIds);

    @Insert
    void insert(ShoppingList sl);

    @Update
    void update(ShoppingList sl);

    @Delete
    void delete(ShoppingList sl);

    @Query("DELETE FROM shopping_lists WHERE productListId = :shoppingListId")
    void deleteProductsInList(String shoppingListId);
    @Query("DELETE FROM shopping_lists")
    void nukeTable();
}
