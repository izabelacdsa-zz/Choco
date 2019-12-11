package com.order.orderlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.BaseViewModel
import com.base.R
import com.extensions.HasError
import com.extensions.TYPE_GENERIC_ERROR
import com.extensions.TYPE_NETWORK_CONNECTION_ERROR
import com.github.kittinunf.result.coroutines.SuspendableResult
import com.network.model.orderlist.OrderListResponse
import com.order.orderlist.repository.OrderListRepository
import kotlinx.coroutines.launch
import java.io.IOException

class OrderListViewModel : BaseViewModel() {

    private val orderListRepository = OrderListRepository()

    val mutableLiveDataOrderListSuccess = MutableLiveData<List<OrderListResponse>>()
    val mutableLiveDataOrderListError = MutableLiveData<HasError>()

    suspend fun getOrderList(token: String) {
        mutableLiveDataLoading.postValue(true)
        viewModelScope.launch {
            SuspendableResult.of<List<OrderListResponse>, Exception> {
                orderListRepository.getOrderList(token)
            }.fold(
                success = {
                    mutableLiveDataOrderListSuccess.postValue(it)
                    mutableLiveDataLoading.postValue(false)

                }, failure = {
                    mutableLiveDataOrderListError.postValue(parseException(it))
                    mutableLiveDataLoading.postValue(false)
                }
            )
        }
    }

    private fun parseException(exception: java.lang.Exception): HasError {
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