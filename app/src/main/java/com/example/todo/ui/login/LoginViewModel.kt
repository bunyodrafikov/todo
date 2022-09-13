package com.example.todo.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.retrofit.ReqresAPI
import com.example.todo.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val api: ReqresAPI) : ViewModel() {

    val loggedToken = MutableLiveData<String>()

    fun login(email: String, password: String) {
        val user = User(email, password)
        viewModelScope.async(Dispatchers.IO) {
            val call = api.sendData(user)
            Log.d("RetrofitCall", "Body: ${call.body()}")
            if (call.isSuccessful) {
                loggedToken.postValue(call.body()!!.token)
                Log.d("RetrofitCall", "Token: ${loggedToken.value}")
            }
        }
    }

    fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }
}
