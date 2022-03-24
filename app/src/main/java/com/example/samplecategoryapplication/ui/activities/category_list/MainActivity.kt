package com.example.samplecategoryapplication.ui.activities.category_list

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplecategoryapplication.R
import com.example.samplecategoryapplication.data.model.CategoryData
import com.example.samplecategoryapplication.ui.activities.category_create.CategoryCreateActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CategoryListViewModel by viewModels {
        viewModelFactory
    }
    private var alMainCategories : MutableList<CategoryData> = mutableListOf()
    var adapter : CategoryAdapter?=null
    var rvCategories : RecyclerView?=null
    var fabCategory :FloatingActionButton?=null
    private var searchHint: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvCategories = findViewById(R.id.rv_categories)
        fabCategory = findViewById(R.id.add_category)
        var searchView = findViewById<SearchView>(R.id.simpleSearchView)
        fabCategory?.setOnClickListener {
            var intent = Intent(this,CategoryCreateActivity::class.java)
            startActivity(intent)
        }
        val layoutManager1 = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvCategories!!.setHasFixedSize(true)
        rvCategories!!.layoutManager = layoutManager1

        viewModel?.getCategoryListFromDb(this)
        searchView.queryHint = searchHint
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }

        })
        viewModel?.categories?.observe(this,{
            it?.observe(this,{
                if (it?.isEmpty()!!){
                    viewModel?.fetchCategoryList(this)
                }else{
                    it?.sortBy {category ->
                        category?.name?.get(0)?.value}
                    adapter = CategoryAdapter(this,populateCategoryList(it))
                    rvCategories!!.adapter = adapter

                }
            })

        })



    }
    fun populateCategoryList(list: MutableList<CategoryData>) : MutableList<CategoryData>{
        alMainCategories.clear()
        var categoryList = list.groupBy { it.parentID }
        categoryList?.entries?.let {
            it?.forEach {
                var key = it?.key
                if (key == "0"){
                    alMainCategories.addAll(it?.value)
                }
            }
        }
        categoryList?.entries?.let {
            it?.forEach {
                var key = it?.key
                if (key != "0"){
                    alMainCategories?.forEachIndexed { index, categoryData ->
                        if (key == categoryData.categoryId){
                            alMainCategories.get(index).subCategory?.addAll(it?.value)
                        }else{
                            alMainCategories.get(index).subCategory?.addAll(mutableListOf())
                        }
                    }
                }

            }
        }

        return alMainCategories
//        categories?.postValue(alMainCategories)
    }
}