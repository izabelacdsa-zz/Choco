package com.login.instrumented.ui

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import com.login.R
import com.login.activity.LoginActivity
import com.login.instrumented.testutils.AssetsLoader
import com.login.instrumented.testutils.enqueue200Response
import com.order.orderlist.ui.activity.OrderListActivity
import okhttp3.mockwebserver.MockWebServer

class LoginActivityRobot(private val server: MockWebServer) {

    lateinit var scenario: ActivityScenario<LoginActivity>

    fun launchActivity() = apply {
        val intent = Intent(ApplicationProvider.getApplicationContext(), LoginActivity::class.java)
        scenario = ActivityScenario.launch(intent)
    }

    fun givenLogin200Response() = apply {
        val response = AssetsLoader.loadAsset("post_add_user_response_200.json")
        server.enqueue200Response(response)
    }

    fun whenClickOnLogin() = apply {
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
            .perform(ViewActions.click())
    }

    fun whenClickEmailInput() = apply {
        Espresso.onView(ViewMatchers.withId(R.id.tilLoginEmail))
            .perform(ViewActions.click())
    }

    fun whenClickPasswordInput() = apply {
        Espresso.onView(ViewMatchers.withId(R.id.tilLoginPassword))
            .perform(ViewActions.click())
    }

    fun whenTypeEmail(email: String) = apply {
        Espresso.onView(ViewMatchers.withId(R.id.etLoginEmail))
            .perform(ViewActions.typeText(email), ViewActions.closeSoftKeyboard())
    }

    fun whenTypePassword(password: String) = apply {
        Espresso.onView(ViewMatchers.withId(R.id.etLoginPassword))
            .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard())
    }

    fun thenLoginWithSuccess() = apply {
        intended(hasComponent(OrderListActivity::class.java.name))
    }

    fun thenLoginButtonIsEnabled() = apply {
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }
}
