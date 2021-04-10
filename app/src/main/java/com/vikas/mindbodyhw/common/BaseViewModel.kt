package com.vikas.mindbodyhw.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class BaseViewModel(application: Application) : AndroidViewModel(application){
    var errorMsg = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

}