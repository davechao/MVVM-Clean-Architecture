package com.test.mvvm.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

open class BaseViewModel() : ViewModel(), KoinComponent {

    val toastData = MutableLiveData<String>()
    val dialogData = MutableLiveData<String>()
    val navigateView = MutableLiveData<Int>()
    val isLoading = MutableLiveData<Boolean>().also { it.value = false }
}