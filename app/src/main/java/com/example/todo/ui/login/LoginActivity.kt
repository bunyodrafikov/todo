package com.example.todo.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.R
import com.example.todo.databinding.ActivityLoginBinding
import com.example.todo.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)

        val username = binding.username
        val password = binding.password
        val login = binding.loginButton

        viewModel.loggedToken.observe(this) { token ->
            if (token != null) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("token", token)
                Toast.makeText(
                    applicationContext,
                    "SUCCESS \n${token}",
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
        }

        login.setOnClickListener {
            if (viewModel.isUserNameValid(username.text.toString())) {
                viewModel.login(username.text.toString(), password.text.toString())
            } else showInvalidMail(R.string.invalid_email)
        }
    }

    private fun showInvalidMail(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}
