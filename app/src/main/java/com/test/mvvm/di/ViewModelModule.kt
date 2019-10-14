package com.test.mvvm.di

import com.test.mvvm.view.home.HomeViewModel
import com.test.mvvm.view.main.MainViewModel
import com.test.mvvm.view.personal.PersonalViewModel
import com.test.mvvm.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel() }
    viewModel { HomeViewModel() }
    viewModel { PersonalViewModel() }
}