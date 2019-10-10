package com.test.mvvm

import android.app.Application
import com.test.mvvm.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    companion object {
        lateinit var self: Application
        fun applicationContext(): Application {
            return self
        }
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)


        RxJavaPlugins.setErrorHandler {
            val errMsg = "### RxJavaPlugins get error: ${it.message}"
            Timber.e(errMsg)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
