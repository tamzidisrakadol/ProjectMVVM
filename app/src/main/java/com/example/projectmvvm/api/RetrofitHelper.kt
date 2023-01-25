package com.example.projectmvvm.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val BaseURl = "https://quotable.io/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}