package com.example.samplecategoryapplication.data.database.converters;

import androidx.room.TypeConverter;

import com.example.samplecategoryapplication.data.model.CategoryData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
//class generated for converting category object to string and vice-versa for database operations

public class CategoryTypeConverters {
    @TypeConverter
    public static List<CategoryData> stringToCategory(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CategoryData>>() {}.getType();
        List<CategoryData> category = gson.fromJson(json, type);
        return category;
    }

    @TypeConverter
    public static String categoryToString(List<CategoryData> category) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CategoryData>>() {}.getType();
        String json = gson.toJson(category, type);
        return json;
    }
}

