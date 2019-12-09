package com.extensions

data class HasError(
    val type: Int = TYPE_GENERIC_ERROR,
    val statusCode: Int = 0,
    val message: String? = null,
    val descriptionRes: Int? = null,
    val drawableRes: Int? = null
)

const val TYPE_GENERIC_ERROR = 0
const val TYPE_NETWORK_CONNECTION_ERROR = 1

