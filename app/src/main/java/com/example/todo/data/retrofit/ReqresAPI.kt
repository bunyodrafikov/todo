package com.example.todo.data.retrofit

import com.example.todo.models.LoggedInUser
import com.example.todo.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ReqresAPI {
    @POST(value = "login")
    suspend fun sendData(
        @Body user: User
    ): Response<LoggedInUser>
}
