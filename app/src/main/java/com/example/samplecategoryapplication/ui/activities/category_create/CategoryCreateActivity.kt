package com.example.samplecategoryapplication.ui.activities.category_create

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.samplecategoryapplication.R
import com.example.samplecategoryapplication.databinding.ActivityCategoryDetailsBinding
import com.example.samplecategoryapplication.utils.URIPathHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class CategoryCreateActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CategoryCreateViewModel by viewModels {
        viewModelFactory
    }
    private var binding : ActivityCategoryDetailsBinding?=null
    private lateinit var categoryMenu: PopupMenu

    private val PERMISSION_REQUEST_CODE = 200
    val REQUEST_CODE = 100
    private val IMAGE_CAPTURE_CODE = 1001
    var image_uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Create Category")
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = this
        viewModel?.categoryId = getRandomString(20)
//        Log.d("Random Id", getRandomString(20))
        categoryMenu = PopupMenu(this, binding?.layoutFeatured!!)
        val modelListMap = mutableMapOf<String, String>()
        viewModel?.getCategoryListFromDb(this)

        viewModel?.categories?.observe(this, {
            categoryMenu.menu.clear()
            it?.observe(this,{
                if (it?.isNotEmpty()!!) {
                    val modelList = arrayListOf<String>()
                    it?.let {
                        it.forEach { data ->
                            modelList.add(data?.name?.get(0)?.value.toString())
                            modelListMap[data?.name?.get(0)?.value.toString()] = data.categoryId.toString()
                            categoryMenu.menu.add(data?.name?.get(0)?.value.toString())
                        }
                    }

                }
            })

        })
        viewModel?.error?.observe(this,{
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })
        viewModel?.success?.observe(this,{
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            finish()
        })
        binding?.editCategory?.setOnClickListener {
            categoryMenu.show()
        }

        categoryMenu.setOnMenuItemClickListener { item ->
            modelListMap[item?.title]?.let { modelId ->
                viewModel.parentId = modelId

            }
            binding?.editCategory?.setText(item?.title!!)
            true
        }

        binding?.ivAdd?.setOnClickListener {
            if (checkPermission()){

                openGalleryForImage()
            }else{
                requestPermission()
            }
        }
        binding?.radioGroup?.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            // This will get the radiobutton that has changed in its check state
            viewModel?.featured = checkedId == R.id.radio_featured

            // This puts the value (true/false) into the variable

            // If the radiobutton that has changed in check state is now checked...
        })
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    fun getRandomString(length: Int) : String {
        val charset = ('0'..'9') + ('a'..'z')

        return List(length) { charset.random() }
                .joinToString("")
    }

    // Function to check and request permission
    private fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val result2 = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            )
            return   result2 == PackageManager.PERMISSION_GRANTED
        } else {
            return true
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {


                    openGalleryForImage()
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(
                            this,
                            "Permission denied to read your External storage",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
                binding?.ivAttached?.visibility = View.VISIBLE
                binding?.ivAttached?.setImageURI(data?.data)

                val uriPathHelper = URIPathHelper()
                val filePath = uriPathHelper.getPath(this, data?.data!!)
                viewModel?.filePath = filePath

//            binding?.profileImage!!.setImageURI(data?.data) // handle chosen image
            }

        } catch (e: Exception) {

        } catch (e: java.lang.Exception) {

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