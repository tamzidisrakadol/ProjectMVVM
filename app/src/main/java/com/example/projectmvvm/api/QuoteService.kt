package com.example.projectmvvm.api

import com.example.projectmvvm.model.Quote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    //url: https://quotable.io/quotes?page=1

    @GET("/quotes")
    suspend fun getQuotes(@Query("page")page:Int):Response<Quote>
}