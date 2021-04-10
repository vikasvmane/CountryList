package com.vikas.mindbodyhw.repositories

import com.vikas.mindbodyhw.model.network.RetrofitServices
import com.vikas.mindbodyhw.network.model.Country
import com.vikas.mindbodyhw.network.model.ProvinceItem
import retrofit2.Call
import retrofit2.Callback

class CountryDataRepository(private val serviceInterface: RetrofitServices) :
    CountryDataRepositoryContract {

    override fun fetchCountryList(callback: Callback<List<Country>>): Call<List<Country>> {
        val disposable = serviceInterface.getCountriesList()
        disposable.enqueue(callback)
        return disposable
    }

    override fun fetchCountryDetail(
        callback: Callback<List<ProvinceItem>>,
        id: Int
    ): Call<List<ProvinceItem>> {
        val disposable = serviceInterface.getCountryDetails(countryId = id)
        disposable.enqueue(callback)
        return disposable
    }
}