package com.test.mvvm.di.module

import com.test.mvvm.view.home.HomeFragment
import com.test.mvvm.view.personal.PersonalFragment
import com.test.mvvm.view.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindPersonalFragment(): PersonalFragment
}