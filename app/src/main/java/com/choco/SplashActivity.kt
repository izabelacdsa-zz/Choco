package com.choco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.actions.Actions

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showLogin()
    }

    private fun showLogin() {
        Handler().postDelayed({
            Actions.openLogin(this@SplashActivity)
            finish()
        }, 2000)
    }
}
