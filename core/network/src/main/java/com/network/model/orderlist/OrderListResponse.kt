package com.network.model.orderlist

import com.google.gson.annotations.SerializedName

data class OrderListResponse(
    @SerializedName("Description")
    val description: String,
    @SerializedName("Id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("price")
    val price: Int
)