package com.example.samplecategoryapplication.data.database.converters;

import android.net.Uri;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
//class generated for converting category object to string and vice-versa for database operations

public class ImageTypeConverters {
    @TypeConverter
    public static List<String> stringToImages(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> images = gson.fromJson(json, type);
        return images;
    }

    @TypeConverter
    public static String imagesToString(List<String> images) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        String json = gson.toJson(images, type);
        return json;
    }
}

