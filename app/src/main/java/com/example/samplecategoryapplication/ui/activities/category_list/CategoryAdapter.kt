package com.example.samplecategoryapplication.ui.activities.category_list;

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplecategoryapplication.R
import com.example.samplecategoryapplication.data.model.CategoryData
import com.example.samplecategoryapplication.ui.activities.category_details.CategoryDetailActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.Exception


/**
 * Created by Noushad N on 24-03-2022.
 */
class CategoryAdapter(
        private var mContext: Context,
        private var items: MutableList<CategoryData>
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(),Filterable {
    var countriesFilterList : MutableList<CategoryData>?=null

    init {
        countriesFilterList = items
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(
                view
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder?.tvName?.setText(countriesFilterList?.get(position)?.name?.get(0)?.value)
        if (countriesFilterList?.get(position)?.image!=null && countriesFilterList?.get(position)?.image?.isNotEmpty()!!){
            holder.ivImage?.visibility = View.VISIBLE
            try {
                var file = File(countriesFilterList?.get(position)?.image?.get(0))
                Picasso.get()
                        .load(file)
                        .into(holder.ivImage, object : Callback {
                            override fun onSuccess() {
                            }

                            override fun onError(e: Exception?) {
                                if (e!=null){

                                }
                            }
                        })

//                holder.ivImage?.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()))
            }catch (e:Exception){

            }

        }else{
            holder.ivImage?.visibility = View.GONE
        }
        holder?.tvName?.setOnClickListener {
            var intent = Intent(mContext,CategoryDetailActivity::class.java)
            var args = Bundle()
            args.putSerializable("category",countriesFilterList?.get(position))
            intent.putExtras(args)
            mContext?.startActivity(intent)
        }
        holder?.ivArrow?.setOnClickListener {
            if (countriesFilterList?.get(position)?.subCategory?.isNotEmpty()!!){
                if (!countriesFilterList?.get(position)!!.isExpanded!!){
                    countriesFilterList?.get(position)?.isExpanded = true
                    holder?.rvCategories?.visibility = View.VISIBLE
                }else{
                    countriesFilterList?.get(position)?.isExpanded = false
                    holder?.rvCategories?.visibility = View.GONE
                }

            }else{
                holder?.rvCategories?.visibility = View.GONE
            }

        }
        if (countriesFilterList?.get(position)?.subCategory?.isNotEmpty()!!){
            if (countriesFilterList?.get(position)!!.isExpanded!!){
                holder?.rvCategories?.visibility = View.VISIBLE
            }else{
                holder?.rvCategories?.visibility = View.GONE
            }
            holder?.ivArrow?.visibility = View.VISIBLE
            val layoutManager1 = LinearLayoutManager(
                    mContext,
                    LinearLayoutManager.VERTICAL,
                    false
            )
            holder?.rvCategories!!.setHasFixedSize(true)
//            holder?.rvCategories!!.addItemDecoration(
//                DividerItemDecoration(
//                    holder?.rvCategories!!.context,
//                    DividerItemDecoration.VERTICAL
//                )
//            )
            holder?.rvCategories!!.layoutManager = layoutManager1
            var list = countriesFilterList?.get(position)?.subCategory!!
            list?.sortBy { data->
                data?.name?.get(0)?.value
            }
            var adapter = CategoryAdapter(mContext, list)
            holder?.rvCategories?.adapter = adapter
        }else{
            holder?.ivArrow?.visibility = View.GONE
            holder?.rvCategories?.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return countriesFilterList?.size!!
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName = itemView?.findViewById<TextView>(R.id.tv_name)
        var ivArrow = itemView?.findViewById<ImageView>(R.id.iv_arrow)
        var rvCategories = itemView?.findViewById<RecyclerView>(R.id.rv_categories)
        var ivImage = itemView?.findViewById<ImageView>(R.id.iv_image)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                val oReturn = FilterResults()
                val results = mutableListOf<CategoryData>()
                countriesFilterList = items
                if (charSearch.isEmpty()) {
                    countriesFilterList = items
                }else{
                    if (constraint != null) {
                        if (countriesFilterList!!.isNotEmpty()) {
                            for (country in countriesFilterList!!) {
                                if (country?.name?.get(0)?.value?.toLowerCase()?.contains(charSearch)!!){
                                    results.add(country)
                                }
                            }
                            countriesFilterList = results
                        }

                    }
                }
                oReturn.values = countriesFilterList

                return oReturn
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countriesFilterList = results?.values as MutableList<CategoryData>
                notifyDataSetChanged()
            }

        }
    }

//    override fun getFilter(): Filter {
//
//    }


}
