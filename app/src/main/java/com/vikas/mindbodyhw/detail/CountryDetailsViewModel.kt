package com.vikas.mindbodyhw.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.vikas.mindbodyhw.common.BaseViewModel
import com.vikas.mindbodyhw.common.MyApplication
import com.vikas.mindbodyhw.model.network.RetrofitServices
import com.vikas.mindbodyhw.network.model.ProvinceItem
import com.vikas.mindbodyhw.repositories.CountryDataRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountryDetailsViewModel(application: Application) : BaseViewModel(application) {
    var liveDataProvinceResponse = MutableLiveData<List<ProvinceItem>>()
    private var disposable: Call<List<ProvinceItem>>? = null

    @Inject
    lateinit var serviceInterface: RetrofitServices
    private var countryDataRepository: CountryDataRepository

    init {
        MyApplication.appComponent.inject(this)
        countryDataRepository = CountryDataRepository(serviceInterface)
    }

    fun getProvinces(id: Int) {
        isLoading.value = true
        disposable = countryDataRepository.fetchCountryDetail(object : Callback<List<ProvinceItem>> {
            override fun onResponse(
                call: Call<List<ProvinceItem>>,
                response: Response<List<ProvinceItem>>
            ) {
                isLoading.value = false
                liveDataProvinceResponse.value = response.body()
            }

            override fun onFailure(call: Call<List<ProvinceItem>>, t: Throwable) {
                isLoading.value = false
                errorMsg.value = t.message
            }
        }, id = id)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.cancel()
    }
}