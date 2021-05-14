package com.example.digitalisi.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digitalisi.api.RetrofitClient
import com.example.digitalisi.model.User
import com.example.digitalisi.repositories.LoginRepo
import com.example.digitalisi.repositories.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
// COUCHE TAHKI MAA REPO DIMA F CONSTRUCTEUR VAL REPO : DEPENDENCY INJECTION
//injection des dependances
class LoginViewModel (private val loginRepo: LoginRepo, private val userRepo : UserRepo ) : ViewModel(){ // () bech yeriti mel constructeur
    var loginResponse: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    val UserIDResp : MutableLiveData<Response<User>> = MutableLiveData()


    fun login(username:String, password:String){
        viewModelScope.launch(Dispatchers.IO){
            val response = loginRepo.login(username, password)
            loginResponse.postValue(response);
        }
    }

    fun getUserID(cookie : String) =  viewModelScope.launch(Dispatchers.IO) {
        try {
            UserIDResp.postValue(userRepo.getUserID(cookie))
        }
        catch(exception: HttpException){
            return@launch
        }
    }
}