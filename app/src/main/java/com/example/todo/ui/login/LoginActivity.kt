package com.example.todo.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.models.LoggedInUser
import com.example.todo.models.User
import com.example.todo.R
import com.example.todo.data.retrofit.RetrofitClient
import com.example.todo.databinding.ActivityLoginBinding
import com.example.todo.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)

        val username = binding.username
        val password = binding.password
        val login = binding.loginButton

        login.setOnClickListener {
            if (isUserNameValid(username.text.toString())) {
                login(username.text.toString(), password.text.toString())
            } else showInvalidMail(R.string.invalid_email)
        }
    }

    private fun showInvalidMail(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun login(email: String, password: String) {
        try {
            val user = User(email, password)
            val call = RetrofitClient.api.sendData(user)
            call.enqueue(
                object : Callback<LoggedInUser> {
                    override fun onResponse(
                        call: Call<LoggedInUser>,
                        response: Response<LoggedInUser>
                    ) {
                        if (response.isSuccessful) {
                            val resObj: LoggedInUser = response.body()!!
                            if (resObj.token != "") {
                                //login start main activity
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                intent.putExtra("token", resObj.token)
                                Toast.makeText(
                                    applicationContext,
                                    "SUCCESS \n${resObj.token}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "The username or password is incorrect",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "The username or password is incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<LoggedInUser>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                }
            )
        } catch (e: Throwable) {
            Log.d("LOGGER", e.message.toString())
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }
}
