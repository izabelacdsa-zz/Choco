package com.actions

import android.content.Context
import android.content.Intent

object Actions {

    private const val ACTION_LOGIN =
        "com.features.login.activity.LoginActivity"
    private const val ACTION_ORDER_LIST =
        "com.features.order.orderlist.ui.activity.OrderListActivity"

    enum class Extras {
        EXTRA_TOKEN_LOGIN
    }

    fun openLogin(
        context: Context
    ) {
        context.startActivity(internalIntent(context, ACTION_LOGIN))
    }

    fun openLoginFromOrderList(
        context: Context
    ) {
        context.startActivity(internalIntent(context, ACTION_LOGIN)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    fun openOrderList(
        context: Context,
        token: String
    ) {
        context.startActivity(
            internalIntent(context, ACTION_ORDER_LIST)
                .putExtra(Extras.EXTRA_TOKEN_LOGIN.name, token)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}