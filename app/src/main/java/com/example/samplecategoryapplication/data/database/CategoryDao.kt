package com.example.samplecategoryapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.samplecategoryapplication.data.model.CategoryData
/**
 * Created by Noushad N on 23-03-2022.
 */
@Dao
interface CategoryDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(categoryData:  CategoryData?) : Long

    @Query("SELECT * FROM category " )
    fun getCategories() : LiveData<MutableList<CategoryData>>

}
