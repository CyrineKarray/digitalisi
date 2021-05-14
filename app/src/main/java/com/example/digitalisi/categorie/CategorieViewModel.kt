package com.example.digitalisi.categorie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digitalisi.model.Categorie
import com.example.digitalisi.repositories.CategorieRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class CategorieViewModel (private val categRepo: CategorieRepo) : ViewModel() {
    var categoriesResponse: MutableLiveData<Response<List<Categorie>>> = MutableLiveData()

    fun getCategories(cookie : String , p : Int) = viewModelScope.launch(Dispatchers.IO) {
        try{
            val response = categRepo.getCategories(cookie, p)
            Log.d("hhhhhhhhhhhhh",response.code().toString())
            categoriesResponse.postValue(response)
        }catch(exception : HttpException){
            return@launch
        }
    }
}