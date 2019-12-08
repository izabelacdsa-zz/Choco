package com.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.BaseViewModel
import com.base.R
import com.extensions.HasError
import com.extensions.TYPE_GENERIC_ERROR
import com.extensions.TYPE_NETWORK_CONNECTION_ERROR
import com.github.kittinunf.result.coroutines.SuspendableResult
import com.login.repository.LoginRepository
import com.network.model.login.LoginRequest
import com.network.model.login.LoginResponse
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class LoginViewModel : BaseViewModel() {

    private val loginRepository = LoginRepository()
    val mutableLiveDataLoginSuccess = MutableLiveData<LoginResponse>()
    val mutableLiveDataLoginError = MutableLiveData<HasError>()

    fun doLogin(email: String, password: String) {
        mutableLiveDataLoading.postValue(true)

        viewModelScope.launch {
            SuspendableResult.of<LoginResponse, Exception> {
                loginRepository.doLogin(LoginRequest(email, password))
            }.fold(
                success = {
                    mutableLiveDataLoginSuccess.postValue(it)
                    mutableLiveDataLoading.postValue(false)
                }, failure = {
                    mutableLiveDataLoginError.postValue(parseException(it))
                    mutableLiveDataLoading.postValue(false)
                }
            )
        }
    }

    private fun parseException(exception: Exception): HasError {
        return when (exception) {
            is IOException -> HasError(
                type = TYPE_NETWORK_CONNECTION_ERROR,
                drawableRes = R.drawable.ic_network_connection_error,
                descriptionRes = R.string.network_error_description
            )
            else -> HasError(
                type = TYPE_GENERIC_ERROR,
                drawableRes = R.drawable.ic_generic_error,
                descriptionRes = R.string.generic_error_description
            )
        }
    }
}





