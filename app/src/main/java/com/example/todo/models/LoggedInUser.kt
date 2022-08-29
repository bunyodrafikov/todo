package com.example.todo.models

import com.google.gson.annotations.SerializedName

data class LoggedInUser(
    @SerializedName("token")
    val token: String
)
