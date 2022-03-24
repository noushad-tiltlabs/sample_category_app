package com.example.samplecategoryapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.samplecategoryapplication.data.database.converters.CategoryTypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Noushad N on 23-03-2022.
 */


class Category : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @SerializedName("data")
    @ColumnInfo(name = "data")
    @Expose
    val data: List<CategoryData>? = listOf()
}