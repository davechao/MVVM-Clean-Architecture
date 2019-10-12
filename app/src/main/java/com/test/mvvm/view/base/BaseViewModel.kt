package com.test.mvvm.view.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val compositeDisposable by lazy { CompositeDisposable() }

    val toastData = MutableLiveData<String>()
    val dialogData = MutableLiveData<String>()
    val navigateView = MutableLiveData<Int>()
    val isLoading = MutableLiveData<Boolean>().also { it.value = false }

    override fun onCleared() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onCleared()
    }
}