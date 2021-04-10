package com.vikas.mindbodyhw.model.network

import com.vikas.mindbodyhw.network.model.Country
import com.vikas.mindbodyhw.network.model.ProvinceItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitServices {
    @GET("rest/worldregions/country")
    fun getCountriesList(): Call<List<Country>>

    @GET("rest/worldregions/country/{id}/province")
    fun getCountryDetails(@Path("id") countryId: Int): Call<List<ProvinceItem>>
}