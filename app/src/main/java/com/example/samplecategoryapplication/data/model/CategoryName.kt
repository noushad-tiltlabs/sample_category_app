package com.example.samplecategoryapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Noushad N on 23-03-2022.
 */
@Entity
data class CategoryName (
    @SerializedName("_id")
    @Expose
    @ColumnInfo(name ="_id")
    val id: String? = null,

    @SerializedName("language")
    @Expose
    @ColumnInfo(name ="language")
    var language: String? = null,

    @SerializedName("value")
    @Expose
    @ColumnInfo(name ="value")
    var value: String? = null
):Serializable{
}