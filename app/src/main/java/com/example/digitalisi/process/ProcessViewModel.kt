package com.example.digitalisi.process

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digitalisi.model.Process
import com.example.digitalisi.repositories.ProcessRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class ProcessViewModel (private val procRepo: ProcessRepo) : ViewModel() {

        var processResponse: MutableLiveData<Response<List<Process>>> = MutableLiveData()

        fun getProcessus(cookie : String, p : Int, c : Int, userId : String, categorieId : String) = viewModelScope.launch(Dispatchers.IO) {
            try{

                val response = procRepo.getProcessus(cookie,p,c,userId,categorieId)
                Log.d("hahahahahah",response.code().toString())
                processResponse.postValue(response)
            }catch(exception : HttpException){
                Log.d("okayy","hhh")
            }
        }

}