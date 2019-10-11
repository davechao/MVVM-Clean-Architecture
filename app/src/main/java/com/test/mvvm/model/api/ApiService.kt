package com.test.mvvm.model.api

import com.test.mvvm.model.api.bean.UserDetailItem
import com.test.mvvm.model.api.bean.UserItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users")
    suspend fun fetchUsers(): ArrayList<UserItem>

    @GET("/users/{login}")
    suspend fun fetchUserDetail(@Path("login") login: String): UserDetailItem
}