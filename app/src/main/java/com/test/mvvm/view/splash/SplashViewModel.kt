package com.test.mvvm.view.splash

import android.app.Application
import com.test.mvvm.R
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.view.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    private val applicationContext = app.applicationContext

    fun autoLogin() {
        navigateView.value = R.id.action_splashFragment_to_homeFragment
    }

}