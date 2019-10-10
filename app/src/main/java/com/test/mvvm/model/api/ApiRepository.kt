package com.test.mvvm.model.api

import com.google.gson.Gson

class ApiRepository(
    private val apiService: ApiService,
    private val gson: Gson
) {

    companion object {
        const val X_APP_VERSION = "x-app-version"
        const val BEARER = "Bearer "
        const val AUTHORIZATION = "Authorization"
    }

}