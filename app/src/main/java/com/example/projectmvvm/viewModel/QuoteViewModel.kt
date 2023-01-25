package com.example.projectmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmvvm.model.Quote
import com.example.projectmvvm.repository.QuoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(val repository: QuoteRepo) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuote(1)
        }
    }

    val quote: LiveData<Quote>
        get() = repository.quotes
}