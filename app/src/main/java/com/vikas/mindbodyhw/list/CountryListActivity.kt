package com.vikas.mindbodyhw.list

import android.content.Intent
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
import com.vikas.mindbodyhw.detail.CountryDetailsActivity
import com.vikas.mindbodyhw.network.model.Country

class CountryListActivity : AppCompatActivity(), OnCountryItemClickListener {

    private var countryList: List<Country> = emptyList()
    private lateinit var recyclerViewCountryList: RecyclerView
    private lateinit var adapter: CountryRecyclerViewAdapter
    private lateinit var viewModel: CountryListViewModel
    private lateinit var errorLayout: LinearLayout
    private lateinit var errorTextView: TextView
    private lateinit var btnRetry: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewCountryList = findViewById(R.id.countryRecyclerView)
        errorLayout = findViewById(R.id.errorView)
        errorTextView = findViewById(R.id.titleTextView)
        btnRetry = findViewById(R.id.btnRetry)
        progressBar = findViewById(R.id.progressBar)
        emptyView = findViewById(R.id.emptyLayout)

        adapter =
            CountryRecyclerViewAdapter(countryList = countryList, countryItemClickListener = this)
        recyclerViewCountryList.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(CountryListViewModel::class.java)
        setObservers()
        if (viewModel.liveDataCountryList.value.isNullOrEmpty())
            viewModel.getCountryList()
    }

    private fun setObservers() {
        viewModel.liveDataCountryList.observe(this, {
            if (it.isEmpty())
                emptyView.visibility = VISIBLE
            else {
                //In case of successful fetch, removes negative layouts
                emptyView.visibility = GONE
                recyclerViewCountryList.visibility = VISIBLE
                errorLayout.visibility = GONE

                countryList = it
                adapter.updateList(countryList)
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
            recyclerViewCountryList.visibility = GONE

            errorLayout.visibility = VISIBLE
            errorTextView.text = it
            btnRetry.setOnClickListener {
                errorLayout.visibility = GONE
                viewModel.getCountryList()
            }
        })
    }

    override fun onCountryClick(id: Int) {
        val intent = Intent(this, CountryDetailsActivity::class.java)
        intent.putExtra(COUNTRY_ID, id)
        startActivity(intent)
    }

    companion object {
        const val COUNTRY_ID = "id"
    }
}