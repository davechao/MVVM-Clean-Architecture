package com.test.mvvm.view.splash

import com.test.mvvm.R
import com.test.mvvm.view.base.BaseViewModel

class SplashViewModel : BaseViewModel() {

    fun autoLogin() {
        navigateView.value = R.id.action_splashFragment_to_homeFragment
    }

}