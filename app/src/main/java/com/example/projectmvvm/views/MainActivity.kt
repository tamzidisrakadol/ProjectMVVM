package com.example.projectmvvm.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.projectmvvm.QuoteApplication
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

        //create an application class
        val repo = (application as QuoteApplication).repository

        //init viewModel
        quoteViewModel = ViewModelProvider(this,QuoteViewModelFactory(repo)).get(QuoteViewModel::class.java)

        //observe quote form QuoteViewModel
        quoteViewModel.quote.observe(this, Observer {
            Toast.makeText(this, "size"+it.results.size, Toast.LENGTH_SHORT).show()
        })
    }
}