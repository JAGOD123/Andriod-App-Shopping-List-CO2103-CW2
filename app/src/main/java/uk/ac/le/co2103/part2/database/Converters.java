package uk.ac.le.co2103.part2.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import uk.ac.le.co2103.part2.model.Product;

public class Converters {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<Product> fromString(String value) {
        Type listType = new TypeToken<List<Product>>(){}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Product> list) {
        return gson.toJson(list);
    }
}
