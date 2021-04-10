package com.vikas.mindbodyhw.detail

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vikas.mindbodyhw.R
import com.vikas.mindbodyhw.list.CountryListActivity.Companion.COUNTRY_ID
import com.vikas.mindbodyhw.network.model.ProvinceItem

class CountryDetailsActivity : AppCompatActivity() {

    private var provinceList: List<ProvinceItem> = emptyList()
    private lateinit var recyclerViewProvince: RecyclerView
    private lateinit var adapter: ProvincesRecyclerViewAdapter
    private lateinit var viewModel: CountryDetailsViewModel
    private lateinit var errorLayout: LinearLayout
    private lateinit var errorTextView: TextView
    private lateinit var btnRetry: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyView: LinearLayout
    private var selectedCountryId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        recyclerViewProvince = findViewById(R.id.provinceRecyclerView)
        errorLayout = findViewById(R.id.errorView)
        errorTextView = findViewById(R.id.titleTextView)
        btnRetry = findViewById(R.id.btnRetry)
        progressBar = findViewById(R.id.progressBar)
        emptyView = findViewById(R.id.emptyLayout)

        adapter =
            ProvincesRecyclerViewAdapter(provinces = provinceList)
        recyclerViewProvince.adapter = adapter
        selectedCountryId = intent.getIntExtra(COUNTRY_ID, 0)

        viewModel = ViewModelProviders.of(this).get(CountryDetailsViewModel::class.java)
        setObservers()
        if (viewModel.liveDataProvinceResponse.value.isNullOrEmpty())
            viewModel.getProvinces(selectedCountryId)
    }

    private fun setObservers() {
        viewModel.liveDataProvinceResponse.observe(this, {
            //In case of successful fetch
            if (it.isEmpty())
                emptyView.visibility = VISIBLE
            else {
                //In case of successful fetch, removes negative layouts
                emptyView.visibility = GONE
                recyclerViewProvince.visibility = VISIBLE
                errorLayout.visibility = GONE

                provinceList = it
                adapter.updateList(provinceList)
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.isLoading.observe(this, {
            when (it) {
                true -> progressBar.visibility = VISIBLE
                false -> progressBar.visibility = GONE
            }
        })
        viewModel.errorMsg.observe(this, {
            emptyView.visibility = GONE
            recyclerViewProvince.visibility = GONE
            errorLayout.visibility = VISIBLE
            errorTextView.text = it
            btnRetry.setOnClickListener {
                errorLayout.visibility = GONE
                viewModel.getProvinces(selectedCountryId)
            }
        })
    }
}