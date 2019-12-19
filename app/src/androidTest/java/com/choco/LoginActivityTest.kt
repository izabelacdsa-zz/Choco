package com.choco

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.login.activity.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private var robot= LoginActivityRobot()

    @Rule
    @JvmField
    val rule = IntentsTestRule<LoginActivity>(LoginActivity::class.java, true, true)

    @Test
    fun shouldOpenScreenLoginSuccess_WhenEmailAndPasswordFieldsAreFilled() {
        robot
            .whenClickEmailInput()
            .whenTypeEmail("user@choco.com")
            .whenClickPasswordInput()
            .whenTypePassword("chocorian")
            .whenClickOnLogin()
    }
}