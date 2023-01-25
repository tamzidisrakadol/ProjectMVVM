package com.example.projectmvvm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projectmvvm.model.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quote:List<Result>)

    @Query("SELECT * FROM quoteTable")
    suspend fun getQuotes():List<Result>



}