package uk.ac.le.co2103.part2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import uk.ac.le.co2103.part2.model.ShoppingList;

@Database(entities = {ShoppingList.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class ShoppingListDatabase extends RoomDatabase {
    private static ShoppingListDatabase INSTANCE;

    public abstract ShoppingListDao shoppingListDao();

    public static synchronized ShoppingListDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ShoppingListDatabase.class, "shopping_list_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
