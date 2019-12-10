package com.order.orderlist.repository

import com.network.config.NetworkConfig

class OrderListRepository {
    suspend fun getOrderList(token: String)=
        NetworkConfig.apiInstance().getOrderListAsync(token).await()

}