package com.network.api

import com.network.model.login.LoginResponse
import com.network.model.login.LoginRequest
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServer {

    @POST("login")
    fun postLoginAsync(
        @Body user: LoginRequest
    ): Deferred<LoginResponse>
}