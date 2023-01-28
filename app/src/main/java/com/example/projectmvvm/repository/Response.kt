package com.example.projectmvvm.repository

import com.example.projectmvvm.model.Quote

sealed class Response<T>(val data:T?=null,val errorMsg:String?=null){

    class Loading<T>: Response<T>()
    class Success<T>(data: T?=null):Response<T>(data = data)
    class Error<T>(errorMsges: String):Response<T>(errorMsg = errorMsges)

}
