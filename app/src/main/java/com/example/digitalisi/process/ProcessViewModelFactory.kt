package com.example.digitalisi.process

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.digitalisi.repositories.ProcessRepo

class ProcessViewModelFactory (private val procRepo: ProcessRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProcessViewModel(procRepo) as T

    }
}