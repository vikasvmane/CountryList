package com.vikas.mindbodyhw.common

import android.app.Application
import com.vikas.mindbodyhw.dagger.AppComponent
import com.vikas.mindbodyhw.dagger.DaggerAppComponent
import com.vikas.mindbodyhw.model.network.RetrofitModule

class MyApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().retrofitModule(RetrofitModule()).build()
    }
}