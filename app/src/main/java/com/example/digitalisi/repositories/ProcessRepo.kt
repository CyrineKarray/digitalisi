package com.example.digitalisi.repositories

import com.example.digitalisi.api.RetrofitClient
import com.example.digitalisi.model.Process
import retrofit2.Response

class ProcessRepo {
    suspend fun getProcessus(cookie : String, p : Int,    c : Int,
                             userId : String,
                             categoryId : String) : Response<List<Process>>
    {
        return RetrofitClient.apiService.getProcessList(cookie,p, c, userId, categoryId)

    }
}