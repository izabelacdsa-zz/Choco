package com.network.model.orderlist

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
): Parcelable