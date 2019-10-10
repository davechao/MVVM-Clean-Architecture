package com.test.mvvm.view.personal

import android.app.Application
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.view.base.BaseViewModel
import javax.inject.Inject

class PersonalViewModel @Inject constructor(
    app: Application,
    private val apiRepository: ApiRepository
) : BaseViewModel(app) {

    private val applicationContext = app.applicationContext

}