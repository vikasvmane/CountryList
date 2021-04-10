package com.vikas.mindbodyhw.repositories

import com.vikas.mindbodyhw.network.model.Country
import com.vikas.mindbodyhw.network.model.ProvinceItem
import retrofit2.Call
import retrofit2.Callback

interface CountryDataRepositoryContract {
    fun fetchCountryList(callback: Callback<List<Country>>): Call<List<Country>>
    fun fetchCountryDetail(
        callback: Callback<List<ProvinceItem>>,
        id: Int
    ): Call<List<ProvinceItem>>
}