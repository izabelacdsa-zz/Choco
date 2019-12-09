package com.login.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import com.actions.Actions
import com.extensions.notVisible
import com.extensions.observeNotNull
import com.extensions.showErrorDialog
import com.extensions.showView
import com.login.R
import com.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initObservers()
        initComponents()
    }

    private fun initComponents() {
        onButtonSignInClick()
    }

    private fun onButtonSignInClick() {
        btnLogin.setOnClickListener {
            val email = etLoginEmail.text.toString()
            val password = etLoginPassword.text.toString()

            if (email.trim().isEmpty() || password.trim().isEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    R.string.login_fill_spaces,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                loginViewModel.doLogin(email, password)
            }
        }
    }

    private fun initObservers() = with(loginViewModel) {
        mutableLiveDataLoginSuccess.observeNotNull(this@LoginActivity) { userData ->
            Toast.makeText(this@LoginActivity, "Its toast!", Toast.LENGTH_SHORT).show()
//            val token = userData.token
//            Actions.openOrderList(this@LoginActivity, token)
//            finish()
        }

        mutableLiveDataLoading.observeNotNull(this@LoginActivity) { showProgress ->
            progressBar.showView(showProgress)
            btnLogin.notVisible = showProgress
            if (showProgress) {
                tilLoginEmail.isEnabled = false
                tilLoginPassword.isEnabled = false
            }
        }

        mutableLiveDataLoginError.observeNotNull(this@LoginActivity) {
            showErrorDialog(it)
        }

    }
}
