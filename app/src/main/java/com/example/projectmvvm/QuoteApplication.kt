package com.example.projectmvvm

import android.app.Application
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.projectmvvm.api.QuoteService
import com.example.projectmvvm.api.RetrofitHelper
import com.example.projectmvvm.database.QuoteDatabase
import com.example.projectmvvm.repository.QuoteRepo
import com.example.projectmvvm.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication:Application() {

    lateinit var repository:QuoteRepo

    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }


    private fun initialize(){
        //create quoteService for repository
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        //createing database for repository
        val quoteDatabase = QuoteDatabase.getDatabase(applicationContext)

        //create repository for viewModel
        repository = QuoteRepo(quoteService,quoteDatabase,applicationContext)
    }

    private fun setUpWorker(){
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerReq = PeriodicWorkRequest.Builder(QuoteWorker::class.java,15,TimeUnit.MINUTES) //at least 15 min
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(workerReq)
    }
}