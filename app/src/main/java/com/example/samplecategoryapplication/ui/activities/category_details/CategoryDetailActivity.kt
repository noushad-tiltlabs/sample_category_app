package com.example.samplecategoryapplication.ui.activities.category_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.samplecategoryapplication.R
import com.example.samplecategoryapplication.data.model.CategoryData
import com.example.samplecategoryapplication.databinding.ActivityCategoryDetailBinding
import com.example.samplecategoryapplication.databinding.ActivityCategoryDetailsBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.Exception

class CategoryDetailActivity : AppCompatActivity() {
    var categoryData : CategoryData?=null
    private var binding : ActivityCategoryDetailBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Category Details")
        if (intent.extras!=null){
            var args = intent.extras
            categoryData = args?.getSerializable("category") as CategoryData?
        }
        if (categoryData!=null){

            binding?.tvName?.setText(categoryData?.name?.get(0)?.value)
            binding?.tvHindiName?.setText(categoryData?.name?.get(1)?.value)
            binding?.tvDescription?.setText(categoryData?.description)
            if (categoryData?.image!=null && categoryData?.image?.isNotEmpty()!!){
                try {
                    var file = File(categoryData?.image?.get(0))
                    Picasso.get()
                        .load(file)
                        .into(binding?.ivImage, object : Callback {
                            override fun onSuccess() {
                            }

                            override fun onError(e: Exception?) {
                                if (e!=null){

                                }
                            }
                        })

//                holder.ivImage?.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()))
                }catch (e: Exception){

                }

            }else{
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)


    }

}