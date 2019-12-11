package com.network.model.orderlist

import com.google.gson.annotations.SerializedName

data class OrderListResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("guid")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val photo: String,
    @SerializedName("price")
    val price: Int
)