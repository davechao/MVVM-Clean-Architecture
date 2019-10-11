package com.test.mvvm.model.api

import com.google.gson.Gson
import com.test.mvvm.model.api.bean.UserDetailItem
import com.test.mvvm.model.api.bean.UserItem

class ApiRepository(
    private val apiService: ApiService,
    private val gson: Gson
) {

    companion object {
        const val X_APP_VERSION = "x-app-version"
        const val BEARER = "Bearer "
        const val AUTHORIZATION = "Authorization"
    }

    suspend fun fetchUsers(): ArrayList<UserItem> {
        return apiService.fetchUsers()
    }

    suspend fun fetchUserDetail(login: String): UserDetailItem {
        return apiService.fetchUserDetail(login)
    }

}