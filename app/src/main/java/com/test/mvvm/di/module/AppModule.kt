package com.test.mvvm.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.mvvm.Constant
import com.test.mvvm.model.pref.Pref
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providePref(gson: Gson): Pref {
        return Pref(gson, Constant.PREFS_NAME)
    }
}
