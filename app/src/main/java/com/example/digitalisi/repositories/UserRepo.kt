package com.example.digitalisi.repositories

import com.example.digitalisi.api.RetrofitClient
import com.example.digitalisi.model.User

import retrofit2.Response

class UserRepo {
    suspend fun getUserID(cookie: String): Response<User> {
        return RetrofitClient.apiService.getUserID(cookie)
    }
}
