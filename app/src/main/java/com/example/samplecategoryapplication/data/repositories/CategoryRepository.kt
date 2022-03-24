package com.example.samplecategoryapplication.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.samplecategoryapplication.data.database.AppDatabase
import com.example.samplecategoryapplication.data.model.CategoryData
import java.io.IOException

/**
 * Created by Noushad N on 23-03-2022.
 */
class CategoryRepository() {

    fun getFileFromAssets(context: Context) : String?{
        var json: String? = null
        json = try {
            val `is` = context?.assets.open("category_list.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun insertCategory(categoryData: CategoryData,context: Context){

        AppDatabase.invoke(context).getCategoryDao()?.upsert(categoryData)
//        db?.getCategoryDao()?.upsert(categoryData)
    }

    fun fetchCategoryList(context: Context) : LiveData<MutableList<CategoryData>>{
        return AppDatabase.invoke(context).getCategoryDao()?.getCategories()
    }
}