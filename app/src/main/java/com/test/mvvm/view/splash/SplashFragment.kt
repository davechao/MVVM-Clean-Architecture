package com.test.mvvm.view.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.test.mvvm.R
import com.test.mvvm.databinding.FragmentSplashBinding
import com.test.mvvm.view.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    companion object {
        private const val SPLASH_TIME_OUT = 3000L
    }

    override fun setViewModelToBinding(binding: FragmentSplashBinding) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateView.observe(this, Observer {
            Navigation.findNavController(view).navigate(it)
        })

        Handler().postDelayed({ viewModel.autoLogin() }, SPLASH_TIME_OUT)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun getViewModelClass(): Class<SplashViewModel>? {
        return SplashViewModel::class.java
    }

    override fun onResume() {
        super.onResume()
        mainViewModel?.bottomNavigationVisibility?.value = View.GONE
    }

}