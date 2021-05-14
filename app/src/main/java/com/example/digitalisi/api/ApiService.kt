package com.example.digitalisi.api

import com.example.digitalisi.model.Categorie
import com.example.digitalisi.model.Process
import com.example.digitalisi.model.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("loginservice")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Response<ResponseBody>

    @GET("API/system/session/unusedId")// url pour getter user id
    suspend fun getUserID (
        @Header("Cookie") cookie: String
    ) : Response<User>

    @GET("portal/custom-page/API/bpm/category")
    suspend fun getCategories(
            @Header("Cookie") cookie: String,
            @Query("p") p : Int)
            : Response<List<Categorie>>

    @GET("API/bpm/process")
    suspend fun getProcessList(
            @Header("Cookie") cookie : String,
            @Query("p") p : Int,
            @Query("c") c : Int,
            @Query("f") userId : String,
            @Query("f") categorieId : String

    ) : Response<List<Process>>
}