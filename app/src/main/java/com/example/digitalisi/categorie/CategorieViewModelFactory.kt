package com.example.digitalisi.categorie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.digitalisi.repositories.CategorieRepo

class CategorieViewModelFactory(private val categRepo: CategorieRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CategorieViewModel(categRepo) as T

    }


}