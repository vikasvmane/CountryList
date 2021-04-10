package com.vikas.mindbodyhw.list

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vikas.mindbodyhw.R
import com.vikas.mindbodyhw.network.model.Country

class CountryRecyclerViewAdapter(
    private var countryList: List<Country>,
    private val countryItemClickListener: OnCountryItemClickListener
) :
    RecyclerView.Adapter<CountryRecyclerViewAdapter.CountryViewHolder>() {
    private lateinit var context: Context

    inner class CountryViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.findViewById(R.id.txtTitle)
        val mDescription: TextView = mView.findViewById(R.id.txtDescription)
        val mThumb: ImageView = mView.findViewById(R.id.imgThumb)

        override fun toString(): String {
            return super.toString() + " '" + mDescription.text + "'"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_list_item, parent, false)
        context = parent.context
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = countryList[position]
        holder.mTitle.text = item.name
        holder.mDescription.text = item.code

        try {
            holder.mThumb.setImageDrawable(
                context.resources.getDrawable(
                    context.resources.getIdentifier(
                        item.code?.toLowerCase(),
                        "drawable", context.packageName
                    )
                )
            )
        } catch (e: Exception) {
            Log.e("flag not found", item.toString())
        }
        holder.itemView.setOnClickListener {
            countryItemClickListener.onCountryClick(item.iD)
        }
    }

    override fun getItemCount() = countryList.size
    fun updateList(countryList: List<Country>) {
        this.countryList = countryList
    }
}