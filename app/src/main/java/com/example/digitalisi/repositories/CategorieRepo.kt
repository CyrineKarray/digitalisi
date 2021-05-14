package com.example.digitalisi.repositories

import com.example.digitalisi.api.RetrofitClient
import com.example.digitalisi.model.Categorie
import retrofit2.Response


class CategorieRepo {
    suspend fun getCategories(cookie : String, p : Int) : Response<List<Categorie>>
    {
        return RetrofitClient.apiService.getCategories(cookie,p)
    }
}
