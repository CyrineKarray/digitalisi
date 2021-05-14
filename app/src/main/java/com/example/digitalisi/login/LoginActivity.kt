package com.example.digitalisi.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.digitalisi.R
import com.example.digitalisi.categorie.CategorieActivity
import com.example.digitalisi.databinding.ActivityLoginBinding
import com.example.digitalisi.repositories.LoginRepo
import com.example.digitalisi.repositories.UserRepo

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel


    var binding: ActivityLoginBinding? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginRepo = LoginRepo()
        val userRepo = UserRepo()
        val viewModelFactory = LoginViewModelFactory(loginRepo,userRepo )
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.rootLayout)

        binding!!.btnLogin.setOnClickListener {// event aal click sur le bouton
            val username = binding!!.edUsername.text.toString().trim()
            val password = binding!!.edPassword.text.toString().trim()
            viewModel!!.login(username, password)

        }
        viewModel.loginResponse.observe(
                this, Observer {
            it?.let { response ->
                if (response.isSuccessful) {

                    Log.d("response", response.headers().values("Set-cookie").toString())

                val cookies: List<String> = response.headers().values("Set-cookie")
                val jsessionid: String = cookies[1].split(";")[0]
                val token: String = cookies[2].split(";")[0]

                //set key:value object to get the processes of the user
                val userCreds = getSharedPreferences("userCreds", Context.MODE_PRIVATE).edit()
                userCreds.apply {
                    putString("JSESSIONID", jsessionid)
                    putString("X-Bonita-API-Token", token)
                }.apply()
                val sessionIdAndToken = "$jsessionid;$token"
                viewModel.getUserID(sessionIdAndToken)

                viewModel.UserIDResp.observe(this, Observer {
                    it?.let { response ->
                        if (response.isSuccessful) {
                            Log.d("response", response.body()!!.session_id);
                            val userID = response.body()!!.user_id;
                            userCreds.apply {
                                putString("userId", userID)

                            }.apply()
                            Log.d("userID", userID)
                            var intent = Intent(this, CategorieActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Log.d("SeesionId and token", sessionIdAndToken);
                            Log.d("response", response.code().toString());
                            Toast.makeText(this@LoginActivity, "Erreur dans UserId !", Toast.LENGTH_SHORT).show()
                        }
                    }

                })

                Toast.makeText(this, "Connexion effectuée avec succés.", Toast.LENGTH_SHORT).show()
                } else {
            Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show()
        }

        }}
        )
        }
    }
