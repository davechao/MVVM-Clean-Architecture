package com.test.mvvm.view.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.test.mvvm.R
import com.test.mvvm.databinding.ActivityMainBinding
import com.test.mvvm.view.base.BaseActivity
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainViewModel = viewModel

        viewModel.bottomNavigationVisibility.observe(this, Observer {
            Timber.d("bottomNavigationVisibility : $it")
            when (it) {
                View.VISIBLE -> binding.bottomNavigation.visibility = it
                View.GONE -> binding.bottomNavigation.visibility = it
            }
        })

        // TODO: Notified when the currently selected bottom navigation item is reselected
        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            if (Navigation.findNavController(
                    this,
                    R.id.nav_host_fragment
                ).currentDestination?.id == R.id.splashFragment
            ) {
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.action_bottom_nav_to_homeFragment)
            }
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.btn_nav_home -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.action_bottom_nav_to_homeFragment)
                    true
                }

                R.id.btn_nav_personal -> {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.action_bottom_nav_to_personalFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel>? {
        return MainViewModel::class.java
    }
}
