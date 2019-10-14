package com.test.mvvm

import android.app.Application
import com.test.mvvm.di.apiModule
import com.test.mvvm.di.appModule
import com.test.mvvm.di.dbModule
import com.test.mvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var self: Application
        fun applicationContext(): Application {
            return self
        }
    }

    init {
        self = this
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val module = listOf(
            appModule,
            apiModule,
            dbModule,
            viewModelModule
        )

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(module)
        }
    }
}
