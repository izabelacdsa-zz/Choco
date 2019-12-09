package com.login.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.login.R
import com.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initObservers()
    }

    private fun initObservers() = with(loginViewModel){
        mutableLiveDataLoginSuccess(this@LoginActivity) {

        }

    }
}
