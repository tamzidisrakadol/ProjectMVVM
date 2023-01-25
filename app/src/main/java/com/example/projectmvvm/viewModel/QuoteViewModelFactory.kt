package com.example.projectmvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectmvvm.repository.QuoteRepo

class QuoteViewModelFactory(private val repository:QuoteRepo):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuoteViewModel(repository) as T
    }
}