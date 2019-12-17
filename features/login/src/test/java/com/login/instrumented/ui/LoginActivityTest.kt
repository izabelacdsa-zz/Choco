package com.login.instrumented.ui

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class LoginActivityTest {

    var server: MockWebServer = MockWebServer()
    private lateinit var robot: LoginActivityRobot

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start(8080)
        robot = LoginActivityRobot(server)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun shouldOpenLoginScreen_WithButtonLoginEnabled() {
        robot
            .givenLogin200Response()
            .launchActivity()
            .thenLoginButtonIsEnabled()
    }

    @Test
    fun shouldLogin_WhenEmailAndPasswordFieldsAreFilledCorrectly() {
        robot
            .givenLogin200Response()
            .launchActivity()
            .whenClickEmailInput()
            .whenTypeEmail("user@choco.com")
            .whenClickPasswordInput()
            .whenTypePassword("chocorian")
            .whenClickOnLogin()
            .thenLoginWithSuccess()
    }
}