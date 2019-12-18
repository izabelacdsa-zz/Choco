package com.login.unit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.extensions.HasError
import com.login.repository.LoginRepository
import com.login.unit.testutils.dummyLoginResponse
import com.login.viewmodel.LoginViewModel
import com.network.model.login.LoginRequest
import com.network.model.login.LoginResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LoginViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    @MockK
    private lateinit var repository: LoginRepository
    @MockK(relaxed = true)
    private lateinit var observerLogin: Observer<LoginResponse>
    @MockK(relaxed = true)
    private lateinit var observerLiveDataProgress: Observer<Boolean>
    @MockK(relaxed = true)
    private lateinit var observerLiveDataError: Observer<HasError>

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this)
        viewModel = LoginViewModel()
        viewModel.mutableLiveDataLoginSuccess.observeForever(observerLogin)
        viewModel.mutableLiveDataLoginError.observeForever(observerLiveDataError)
        viewModel.mutableLiveDataLoading.observeForever(observerLiveDataProgress)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        viewModel.mutableLiveDataLoginSuccess.removeObserver(observerLogin)
        viewModel.mutableLiveDataLoginError.observeForever(observerLiveDataError)
        viewModel.mutableLiveDataLoading.removeObserver(observerLiveDataProgress)
    }

    @Test
    fun `should do login with success`() = runBlocking {

        coEvery {
            repository.doLogin(
                request = LoginRequest(
                    "user@choco.com",
                    "chocorian"
                )
            )

        } returns dummyLoginResponse

        viewModel.doLogin(
            "user@choco.com",
            "chocorian"
        )
        verifySequence {
            observerLiveDataProgress.onChanged(true)
            observerLogin.onChanged(dummyLoginResponse)
            observerLiveDataProgress.onChanged(false)
        }

        verify {
            observerLiveDataError wasNot Called
        }
    }
}