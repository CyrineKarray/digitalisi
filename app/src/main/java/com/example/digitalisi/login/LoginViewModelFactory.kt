package com.example.digitalisi.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.digitalisi.repositories.LoginRepo
import com.example.digitalisi.repositories.UserRepo

class LoginViewModelFactory(private val loginRepo: LoginRepo, private val userRepo: UserRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepo, userRepo) as T
    }
}