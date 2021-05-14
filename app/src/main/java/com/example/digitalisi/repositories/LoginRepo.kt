package com.example.digitalisi.repositories

import com.example.digitalisi.api.ApiService
import com.example.digitalisi.api.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Response

class LoginRepo {
    suspend fun login(username: String, password: String):Response<ResponseBody>{
        return RetrofitClient.apiService.login(username,password);
    }


}