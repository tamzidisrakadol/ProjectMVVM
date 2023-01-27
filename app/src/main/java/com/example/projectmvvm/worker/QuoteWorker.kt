package com.example.projectmvvm.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.projectmvvm.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context,params:WorkerParameters):Worker(context,params) {


    override fun doWork(): Result {

        Log.d("worker","Worker-Called")
        //get the repo from QuoteApplication
        val repo = (context as QuoteApplication).repository

        CoroutineScope(Dispatchers.IO).launch {
            repo.getQuotesInBackground()
        }
        return Result.success()
    }
}