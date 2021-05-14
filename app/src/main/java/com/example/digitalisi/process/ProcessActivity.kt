package com.example.digitalisi.process

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalisi.R
import com.example.digitalisi.adapter.ProcessAdapter
import com.example.digitalisi.repositories.ProcessRepo
import kotlinx.android.synthetic.main.activity_process.*

class ProcessActivity : AppCompatActivity() {

        private val ProcessAdapter by lazy { ProcessAdapter() }

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_process)

            setupRecyclerview()
            val procRepo = ProcessRepo()
            val viewModelFactory = ProcessViewModelFactory(procRepo)

            var viewModel = ViewModelProvider(this, viewModelFactory).get(ProcessViewModel::class.java)

            val userCreds = getSharedPreferences("userCreds", MODE_PRIVATE)
            val jsessionid = userCreds.getString("JSESSIONID", "")
            val token = userCreds.getString("X-Bonita-API-Token", "")
            val userId = userCreds.getString("userId","")
            val categorie_id = intent.getStringExtra("categorie_id")
            val categorie_name = intent.getStringExtra("category_name")
            val sessionIdAndToken = "$jsessionid;$token"



            viewModel.getProcessus(sessionIdAndToken,0,100,"user_id="+userId,"categoryId="+categorie_id, categorie_name!!)


            viewModel.processResponse.observe(
                    this, Observer {
                response ->
                if (response.isSuccessful) {

                    response.body()?.let { ProcessAdapter.setData(it)}
                    Log.d("reponse",response.body().toString())
                }else{
                    Log.d("reponse",response.body().toString())
                    Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
                }
            })

        }

        private fun setupRecyclerview() {
            recycler1.adapter = ProcessAdapter
            recycler1.layoutManager = LinearLayoutManager(this)
        }

    }
