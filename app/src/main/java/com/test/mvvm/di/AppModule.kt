package com.test.mvvm.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.mvvm.Constant
import com.test.mvvm.model.pref.Pref
import org.koin.dsl.module

val appModule = module {
    single { provideMoshi() }
    single { providePref(get()) }
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun providePref(moshi: Moshi): Pref {
    return Pref(moshi, Constant.PREFS_NAME)
}
