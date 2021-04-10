package com.vikas.mindbodyhw.dagger

import com.vikas.mindbodyhw.detail.CountryDetailsViewModel
import com.vikas.mindbodyhw.list.CountryListViewModel
import com.vikas.mindbodyhw.model.network.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {

    fun inject(countryListViewModel: CountryListViewModel)

    fun inject(countryDetailViewModel: CountryDetailsViewModel)
}