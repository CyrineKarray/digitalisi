package com.example.digitalisi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private  val BASE_URL = "http://digitalisi.tn:8080/bonita/"


    private fun BuildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val apiService: ApiService = BuildRetrofit().create(ApiService::class.java)
}