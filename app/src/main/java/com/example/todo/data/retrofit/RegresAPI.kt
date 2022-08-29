package com.example.todo.data.retrofit

import com.example.todo.models.LoggedInUser
import com.example.todo.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ReqresAPI {
    // ANNOTATE WITH POST TO SEND DATA
    @POST(value = "login")
    fun sendData(
        @Body user: User
    ): Call<LoggedInUser> // CALL IS USED TO MAKE AN API CALL
}
