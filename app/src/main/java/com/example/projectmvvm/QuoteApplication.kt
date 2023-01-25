package com.example.projectmvvm

import android.app.Application
import com.example.projectmvvm.api.QuoteService
import com.example.projectmvvm.api.RetrofitHelper
import com.example.projectmvvm.database.QuoteDatabase
import com.example.projectmvvm.repository.QuoteRepo

class QuoteApplication:Application() {

    lateinit var repository:QuoteRepo

    override fun onCreate() {
        super.onCreate()
        initialize()
    }


    private fun initialize(){
        //create quoteService for repository
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        //createing database for repository
        val quoteDatabase = QuoteDatabase.getDatabase(applicationContext)

        //create repository for viewModel
        repository = QuoteRepo(quoteService,quoteDatabase,applicationContext)
    }
}