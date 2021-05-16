package com.pszandi.contactlist.service

import com.pszandi.contactlist.data.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("/api")
    suspend fun getUsers(@Query("results") results: Int): Response<UserListResponse>

}