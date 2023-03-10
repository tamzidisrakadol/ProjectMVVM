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
    private val quoteLiveData = MutableLiveData<Response<Quote>>()


    //encapsulate with liveData for unchangeable
    val quotes: LiveData<Response<Quote>>
        get() = quoteLiveData


    suspend fun getQuote(page: Int) {
    //if net is available it will fetch data from api and save it to room or
        // else it wil show existing data from room


        if (NetworkClass.isInternetAvailable(applicationContext)){

            try {
                val result = quoteService.getQuotes(page)
                if (result?.body() != null) {    //result!=null && result.body()!=null
                    quoteDatabase.quoteDao().insertQuote(result.body()!!.results)
                    quoteLiveData.postValue(Response.Success(result.body()))
                }else{
                    quoteLiveData.postValue(Response.Error("API ERROR"))
                }
            }catch (e:Exception){
                quoteLiveData.postValue(Response.Error(e.message.toString()))
            }

        }else{

            val offlineQuotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = Quote(1,1,1,offlineQuotes,1,1)  //dummy data
            quoteLiveData.postValue(Response.Success(quoteList))
        }
    }



    //work-Manager
    suspend fun getQuotesInBackground(){
        var randomNumber = (Math.random()*10).toInt()
        var result = quoteService.getQuotes(randomNumber)
        if (result?.body()!=null){
            quoteDatabase.quoteDao().insertQuote(result.body()!!.results)
        }
    }

}