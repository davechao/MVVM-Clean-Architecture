package com.test.mvvm.view.main

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.view.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    app: Application,
    private val apiRepository: ApiRepository
) : BaseViewModel(app) {

    val bottomNavigationVisibility = MutableLiveData<Int>().also { it.value = View.VISIBLE }

}