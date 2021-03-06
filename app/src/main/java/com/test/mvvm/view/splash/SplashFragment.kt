package com.test.mvvm.view.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.test.mvvm.R
import com.test.mvvm.databinding.FragmentSplashBinding
import com.test.mvvm.view.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    companion object {
        private const val SPLASH_TIME_OUT = 3000L
    }

    private val viewModel by viewModel<SplashViewModel>()

    override fun setViewModelToBinding(binding: FragmentSplashBinding) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment)
        }, SPLASH_TIME_OUT)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun onResume() {
        super.onResume()
        mainViewModel?.bottomNavigationVisibility?.value = View.GONE
    }

}