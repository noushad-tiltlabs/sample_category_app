package com.example.samplecategoryapplication.data.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.samplecategoryapplication.data.database.converters.CategoryTypeConverters
import com.example.samplecategoryapplication.data.database.converters.ImageTypeConverters
import com.example.samplecategoryapplication.data.database.converters.NameTypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Noushad N on 23-03-2022.
 */
@Entity(tableName = "category")
@TypeConverters(
        NameTypeConverters::class,
        CategoryTypeConverters::class,
        ImageTypeConverters::class
)
data class CategoryData(

        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,

        @SerializedName("categoryId")
        @ColumnInfo(name = "categoryId")
        @Expose
        var categoryId: String? = null,

        @SerializedName("name")
        @ColumnInfo(name = "name")
        @Expose
        var name: List<CategoryName>? = null,

        @SerializedName("slug")
        @ColumnInfo(name = "slug")
        @Expose
        val slug: String? = null,

        @SerializedName("description")
        @ColumnInfo(name = "description")
        @Expose
        var description: String? = null,

        @SerializedName("parentID")
        @ColumnInfo(name = "parentID")
        @Expose
        var parentID: String? = null,

        @SerializedName("type")
        @ColumnInfo(name = "type")
        @Expose
        val type: Int? = null,

        @SerializedName("attributeSet")
        @ColumnInfo(name = "attributeSet")
        @Expose
        val attributeSet: String? = null,

        @SerializedName("categoryNumber")
        @ColumnInfo(name = "categoryNumber")
        @Expose
        val categoryNumber: Int? = 0,

        @SerializedName("level")
        @ColumnInfo(name = "level")
        @Expose
        val level: Int? = null,

        @SerializedName("featured")
        @ColumnInfo(name = "featured")
        @Expose
        var featured: Boolean? = null,

        @SerializedName("icon")
        @Expose
        val icon: String? = null,

        @SerializedName("image")
        @ColumnInfo(name = "image")
        @Expose
        var image: List<String>? = listOf(),

        @SerializedName("status")
        @ColumnInfo(name = "status")
        @Expose
        val status: Boolean? = null,

        @SerializedName("create_date")
        @ColumnInfo(name = "create_date")
        @Expose
        val createDate: String? = null,

        @SerializedName("is_expanded")
        @ColumnInfo(name = "is_expanded")
        @Expose
        var isExpanded: Boolean? = false,

        @SerializedName("suub_category")
        @ColumnInfo(name = "suub_category")
        @Expose
        val subCategory: MutableList<CategoryData>? = mutableListOf()
) : Serializable {
}