package com.network.config

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.network.api.ApiServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {

    private const val BASE_URL = "https://glc4swy1fd.execute-api.eu-west-1.amazonaws.com/choco/"
    private var api: ApiServer? = null

    fun apiInstance(): ApiServer = api ?: Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build().create(ApiServer::class.java)
}