package com.example.todo.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val apiUrl = "https://reqres.in/api/"

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(apiUrl)
        .build()
    val api: ReqresAPI = retrofit.create(ReqresAPI::class.java)
}