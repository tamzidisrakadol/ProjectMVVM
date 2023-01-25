package com.example.projectmvvm.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.projectmvvm.R
import com.example.projectmvvm.api.QuoteService
import com.example.projectmvvm.api.RetrofitHelper
import com.example.projectmvvm.repository.QuoteRepo
import com.example.projectmvvm.viewModel.QuoteViewModel
import com.example.projectmvvm.viewModel.QuoteViewModelFactory
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private val TAG: String = "TAG"
    private lateinit var quoteViewModel: QuoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create quoteService for repository
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        //create repository for viewModel
        val repository = QuoteRepo(quoteService)

        //init viewModel
        quoteViewModel = ViewModelProvider(this,QuoteViewModelFactory(repository)).get(QuoteViewModel::class.java)

        //observe quote form QuoteViewModel
        quoteViewModel.quote.observe(this, Observer {
            Log.d(TAG,it.results.toString())
        })
    }
}