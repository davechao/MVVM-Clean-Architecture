package com.test.mvvm.di.module

import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.test.mvvm.BuildConfig
import com.test.mvvm.Constant
import com.test.mvvm.model.api.ApiRepository
import com.test.mvvm.model.api.ApiService
import com.test.mvvm.model.api.AuthInterceptor
import com.test.mvvm.model.pref.Pref
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class ApiModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(pref: Pref): AuthInterceptor {
        return AuthInterceptor(pref)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiService(gson: Gson, okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constant.API_HOST_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(apiService: ApiService, gson: Gson): ApiRepository {
        return ApiRepository(apiService, gson)
    }
}
