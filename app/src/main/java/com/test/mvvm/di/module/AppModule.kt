package com.test.mvvm.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.mvvm.Constant
import com.test.mvvm.model.pref.Pref
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providePref(moshi: Moshi): Pref {
        return Pref(moshi, Constant.PREFS_NAME)
    }
}
