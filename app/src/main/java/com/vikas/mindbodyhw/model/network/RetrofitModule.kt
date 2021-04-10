package com.vikas.mindbodyhw.model.network

import com.vikas.mindbodyhw.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun getRetrofitServices(): RetrofitServices {
        return getRetrofit().create(RetrofitServices::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL).client(getOkhttp())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getOkhttp(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
            okHttpBuilder.addInterceptor(getHttpLoggingInterceptor())
        return okHttpBuilder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}