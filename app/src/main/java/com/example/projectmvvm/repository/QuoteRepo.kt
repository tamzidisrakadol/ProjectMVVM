package com.example.projectmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectmvvm.api.QuoteService
import com.example.projectmvvm.model.Quote

class QuoteRepo(private val quoteService: QuoteService) {

    //create an quoteLiveData to observe data live
    private val quoteLiveData = MutableLiveData<Quote>()

    //encapsulate with liveData for unchangeable
    val quotes: LiveData<Quote>
        get() = quoteLiveData


    suspend fun getQuote(page: Int) {
        val result = quoteService.getQuotes(page)
        if (result?.body() != null) {    //result!=null && result.body()!=null
            quoteLiveData.postValue(result.body())
        }
    }
}