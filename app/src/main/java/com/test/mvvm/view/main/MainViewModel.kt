package com.test.mvvm.view.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.test.mvvm.view.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    val bottomNavigationVisibility = MutableLiveData<Int>().also { it.value = View.VISIBLE }

}