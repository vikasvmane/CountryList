package com.vikas.mindbodyhw.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vikas.mindbodyhw.R
import com.vikas.mindbodyhw.network.model.ProvinceItem

class ProvincesRecyclerViewAdapter(private var provinces: List<ProvinceItem>) :
    RecyclerView.Adapter<ProvincesRecyclerViewAdapter.ProvinceViewHolder>() {
    private lateinit var context: Context

    inner class ProvinceViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.findViewById(R.id.txtTitle)
        val mDescription: TextView = mView.findViewById(R.id.txtDescription)

        override fun toString(): String {
            return super.toString() + " '" + mDescription.text + "'"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.province_list_item, parent, false)
        context = parent.context
        return ProvinceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        val item = provinces[position]
        holder.mTitle.text = item.name
        holder.mDescription.text = item.code
    }

    override fun getItemCount() = provinces.size
    fun updateList(province: List<ProvinceItem>) {
        this.provinces = province
    }
}