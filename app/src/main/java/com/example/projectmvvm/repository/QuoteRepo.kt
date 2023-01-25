package com.example.projectmvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectmvvm.api.QuoteService
import com.example.projectmvvm.database.QuoteDatabase
import com.example.projectmvvm.model.Quote
import com.example.projectmvvm.utils.NetworkClass

class QuoteRepo(private val quoteService: QuoteService,private val quoteDatabase: QuoteDatabase,private val applicationContext:Context) {

    //create an quoteLiveData to observe data live
    private val quoteLiveData = MutableLiveData<Quote>()


    //encapsulate with liveData for unchangeable
    val quotes: LiveData<Quote>
        get() = quoteLiveData


    suspend fun getQuote(page: Int) {
    //if net is available it will fetch data from api and save it to room or
        // else it wil show existing data from room


        if (NetworkClass.isInternetAvailable(applicationContext)){

            val result = quoteService.getQuotes(page)
            if (result?.body() != null) {    //result!=null && result.body()!=null
                quoteDatabase.quoteDao().insertQuote(result.body()!!.results)
                quoteLiveData.postValue(result.body())
            }

        }else{

            val offlineQuotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = Quote(1,1,1,offlineQuotes,1,1)  //dummy data
            quoteLiveData.postValue(quoteList)
        }



    }
}