package com.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val mutableLiveDataLoading = MutableLiveData<Boolean>()
}