package com.choco

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers

class LoginActivityRobot {

    fun whenClickOnLogin() = apply {
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin))
            .perform(ViewActions.click())
    }

    fun whenClickEmailInput() = apply {
        Espresso.onView(ViewMatchers.withId(R.id.tilLoginEmail))
            .perform(ViewActions.click(), ViewActions.closeSoftKeyboard())
    }

    fun whenClickPasswordInput() = apply {
        Espresso.onView(ViewMatchers.withId(R.id.tilLoginPassword))
            .perform(ViewActions.click(), ViewActions.closeSoftKeyboard())
    }

    fun whenTypeEmail(email: String) = apply {
        Espresso.onView(ViewMatchers.withId(R.id.etLoginEmail))
            .perform(ViewActions.typeText(email), ViewActions.closeSoftKeyboard())
    }

    fun whenTypePassword(password: String) = apply {
        Espresso.onView(ViewMatchers.withId(R.id.etLoginPassword))
            .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard())
    }
}