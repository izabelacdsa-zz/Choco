package com.login.repository

import com.network.config.NetworkConfig
import com.network.model.login.LoginRequest
import com.network.model.login.LoginResponse

class LoginRepository {

    suspend fun doLogin(request: LoginRequest): LoginResponse {
        return NetworkConfig.apiInstance().postLoginAsync(request).await()
    }
}