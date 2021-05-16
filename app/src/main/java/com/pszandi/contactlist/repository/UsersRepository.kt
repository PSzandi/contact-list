package com.pszandi.contactlist.repository

import com.pszandi.contactlist.data.UserListResponse
import com.pszandi.contactlist.service.UserApi
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) {

    suspend fun getUsers(results: Int): Response<UserListResponse> {
        return userApi.getUsers(results)
    }

}