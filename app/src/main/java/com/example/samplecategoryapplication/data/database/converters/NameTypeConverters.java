package com.example.samplecategoryapplication.data.database.converters;

import androidx.room.TypeConverter;

import com.example.samplecategoryapplication.data.model.CategoryData;
import com.example.samplecategoryapplication.data.model.CategoryName;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
//class generated for converting category object to string and vice-versa for database operations

public class NameTypeConverters {
    @TypeConverter
    public static List<CategoryName> stringToCategoryName(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CategoryName>>() {}.getType();
        List<CategoryName> categoryNames = gson.fromJson(json, type);
        return categoryNames;
    }

    @TypeConverter
    public static String categoryNameToString(List<CategoryName> categoryNames) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CategoryName>>() {}.getType();
        String json = gson.toJson(categoryNames, type);
        return json;
    }
}

