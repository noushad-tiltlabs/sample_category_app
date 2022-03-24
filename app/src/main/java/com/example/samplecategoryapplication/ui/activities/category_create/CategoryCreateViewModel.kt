package com.example.samplecategoryapplication.ui.activities.category_create

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplecategoryapplication.data.model.CategoryData
import com.example.samplecategoryapplication.data.model.CategoryName
import com.example.samplecategoryapplication.data.repositories.CategoryRepository
import javax.inject.Inject

/**
 * Created by Noushad N on 23-03-2022.
 */

class CategoryCreateViewModel @Inject constructor(private val repository: CategoryRepository) :ViewModel(){

    val categories = MutableLiveData<LiveData<MutableList<CategoryData>>>()
    var alCategories : MutableList<CategoryData> = mutableListOf()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<String>()
    var parentId = "0"
    var categoryNameEnglish = ""
    var categoryNameHindi = ""
    var categoryDescription = ""
    var categoryId = ""
    var featured = false
    var errorMessage: String = ""
    var filePath : String?=null


    fun getCategoryListFromDb(context: Context){
        categories.postValue(repository?.fetchCategoryList(context))
    }
    fun createCategory(context: Context){

        if (categoryNameEnglish.isEmpty()){
            errorMessage = "Please enter category name in English"

            error?.postValue(errorMessage)
        }else if (categoryNameHindi.isEmpty()){
            errorMessage = "Please enter category name in Hindi"
            error?.postValue(errorMessage)
        }else if (categoryDescription.isEmpty()){
            errorMessage = "Please enter category description"
            error?.postValue(errorMessage)
        }else{
            var categoryData = CategoryData()
            categoryData.categoryId = categoryId
            categoryData.parentID = parentId
            categoryData.featured = featured
            categoryData.description = categoryDescription
            var nameList : MutableList<CategoryName> = mutableListOf()
            var nameEnglish = CategoryName()
            nameEnglish.language = "en"
            nameEnglish.value =categoryNameEnglish
            nameList.add(0,nameEnglish)
            var nameHindi = CategoryName()
            nameHindi.language = "hi"
            nameHindi.value =categoryNameHindi
            nameList.add(nameHindi)
            categoryData?.name = nameList
            var imageList : MutableList<String>?= mutableListOf()
            imageList?.add(filePath!!)
            categoryData?.image = imageList
            repository?.insertCategory(categoryData,context)
            success?.postValue("Category Added")
        }
    }
}