package com.network.api

import com.network.model.login.LoginResponse
import com.network.model.login.LoginRequest
import com.network.model.orderlist.OrderListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServer {

    @POST("login")
    fun postLoginAsync(
        @Body user: LoginRequest
    ): Deferred<LoginResponse>

    @GET("")
    fun getOrderListAsync(
        @Query("token")
        token: String
    ): Deferred<OrderListResponse>
}