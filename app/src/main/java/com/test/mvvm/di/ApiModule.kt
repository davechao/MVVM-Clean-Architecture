package com.test.mvvm.di

import com.test.mvvm.BuildConfig
import com.test.mvvm.Constant
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.ApiService
import com.test.mvvm.model.api.AuthInterceptor
import com.test.mvvm.model.pref.Pref
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { provideAuthInterceptor(get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideApiService(get()) }
    single { provideApiRepository(get()) }
}

fun provideAuthInterceptor(pref: Pref): AuthInterceptor {
    return AuthInterceptor(pref)
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = when (BuildConfig.DEBUG) {
        true -> HttpLoggingInterceptor.Level.BODY
        else -> HttpLoggingInterceptor.Level.NONE
    }
    return httpLoggingInterceptor
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor())
        .build()
}

fun provideApiService(okHttpClient: OkHttpClient): ApiService {
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(Constant.API_HOST_URL)
        .build()
        .create(ApiService::class.java)
}

fun provideApiRepository(apiService: ApiService): ApiRepository {
    return ApiRepository(apiService)
}



