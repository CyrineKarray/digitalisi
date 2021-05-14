package com.example.digitalisi.categorie

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalisi.R
import com.example.digitalisi.adapter.CategorieAdapter
import com.example.digitalisi.repositories.CategorieRepo
import kotlinx.android.synthetic.main.activity_categorie.*

class CategorieActivity : AppCompatActivity() {

    private val CategorieAdapter by lazy { CategorieAdapter()  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_categorie)

        setupRecyclerview()
        val categRepo = CategorieRepo()
        val viewModelFactory = CategorieViewModelFactory(categRepo)

        var viewModel = ViewModelProvider(this, viewModelFactory).get(CategorieViewModel::class.java)

        val userCreds = getSharedPreferences("userCreds", MODE_PRIVATE)
        val jsessionid = userCreds.getString("JSESSIONID", "")
        val token = userCreds.getString("X-Bonita-API-Token", "")
        val sessionIdAndToken = "$jsessionid;$token"


        viewModel.getCategories(sessionIdAndToken,0)

        viewModel.categoriesResponse.observe(
                this, Observer {
                response ->
                if (response.isSuccessful) {

                   response.body()?.let { CategorieAdapter.setData(it)}
                    Log.d("reponse",response.body().toString())
                }else{
                    Log.d("reponse",response.body().toString())
                    Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
                }
        })

    }

    private fun setupRecyclerview() {
        recycler.adapter = CategorieAdapter
        recycler.layoutManager = LinearLayoutManager(this)
    }

}