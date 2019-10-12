package com.test.mvvm.model.api

import com.test.mvvm.model.api.bean.UserDetailItem
import com.test.mvvm.model.api.bean.UserItem
import retrofit2.Response

class ApiRepository(private val apiService: ApiService) {

    companion object {
        const val X_APP_VERSION = "x-app-version"
        const val BEARER = "Bearer "
        const val AUTHORIZATION = "Authorization"
    }

    suspend fun fetchUsers(since: Int, perPage: Int): Response<ArrayList<UserItem>> {
        return apiService.fetchUsers(since, perPage)
    }

    suspend fun fetchUsers(url: String): Response<ArrayList<UserItem>> {
        return apiService.fetchUsers(url)
    }

    suspend fun fetchUserDetail(login: String): UserDetailItem {
        return apiService.fetchUserDetail(login)
    }

}