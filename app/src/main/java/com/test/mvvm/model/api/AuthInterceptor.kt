package com.test.mvvm.model.api

import android.text.TextUtils
import com.test.mvvm.BuildConfig
import com.test.mvvm.model.api.ApiRepository.Companion.AUTHORIZATION
import com.test.mvvm.model.api.ApiRepository.Companion.X_APP_VERSION
import com.test.mvvm.model.pref.Pref
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val pref: Pref) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .addHeader(X_APP_VERSION, BuildConfig.VERSION_NAME)

        if (!TextUtils.isEmpty(pref.token.accessToken)) {
            val auth = StringBuilder(ApiRepository.BEARER)
                .append(pref.token.accessToken)
                .toString()
            requestBuilder.addHeader(AUTHORIZATION, auth)
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}