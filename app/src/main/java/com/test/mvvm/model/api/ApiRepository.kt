package com.test.mvvm.model.api

import com.test.mvvm.model.api.vo.UserDetailItem
import com.test.mvvm.model.api.vo.UserItem
import retrofit2.Response

class ApiRepository(private val apiService: ApiService) {

    companion object {
        const val X_APP_VERSION = "x-app-version"
        const val BEARER = "Bearer "
        const val AUTHORIZATION = "Authorization"
    }

    suspend fun fetchUsers(since: Int, perPage: Int): Response<List<UserItem>> {
        return apiService.fetchUsers(since, perPage)
    }

    suspend fun fetchUsers(url: String): Response<List<UserItem>> {
        return apiService.fetchUsers(url)
    }

    suspend fun fetchUserDetail(username: String): Response<UserDetailItem> {
        return apiService.fetchUserDetail(username)
    }

    suspend fun testNoContentApi(): Response<Void> {
        return apiService.testNoContentApi()
    }

}