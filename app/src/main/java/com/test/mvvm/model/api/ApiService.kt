package com.test.mvvm.model.api

import com.test.mvvm.model.api.bean.UserDetailItem
import com.test.mvvm.model.api.bean.UserItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("/users")
    suspend fun fetchUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Response<ArrayList<UserItem>>

    @GET
    suspend fun fetchUsers(@Url url: String): Response<ArrayList<UserItem>>

    @GET("/users/{username}")
    suspend fun fetchUserDetail(@Path("username") username: String): UserDetailItem
}