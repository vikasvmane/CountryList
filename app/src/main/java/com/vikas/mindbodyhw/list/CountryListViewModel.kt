package com.vikas.mindbodyhw.list

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.vikas.mindbodyhw.common.BaseViewModel
import com.vikas.mindbodyhw.common.MyApplication
import com.vikas.mindbodyhw.model.network.RetrofitServices
import com.vikas.mindbodyhw.network.model.Country
import com.vikas.mindbodyhw.repositories.CountryDataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountryListViewModel(application: Application) : BaseViewModel(application) {
    var liveDataCountryList = MutableLiveData<List<Country>>()
    private var disposable: Call<List<Country>>? = null


    @Inject
    lateinit var serviceInterface: RetrofitServices
    private var countryDataRepository: CountryDataRepository

    init {
        MyApplication.appComponent.inject(this)
        countryDataRepository = CountryDataRepository(serviceInterface)
    }

    fun getCountryList() {
        isLoading.value = true
        disposable = countryDataRepository.fetchCountryList(object : Callback<List<Country>> {
            override fun onResponse(
                call: Call<List<Country>>,
                response: Response<List<Country>>
            ) {
                isLoading.value = false
                liveDataCountryList.value = response.body()
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                isLoading.value = false
                errorMsg.value = t.message
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.cancel()
    }
}