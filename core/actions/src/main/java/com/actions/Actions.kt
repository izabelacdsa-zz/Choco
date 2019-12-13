package com.actions

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.network.model.orderlist.OrderListResponse
import java.util.ArrayList

object Actions {

    private const val ACTION_LOGIN =
        "com.features.login.activity.LoginActivity"
    private const val ACTION_ORDER_LIST =
        "com.features.order.orderlist.ui.activity.OrderListActivity"
    private const val ACTION_ORDER_CHECKOUT =
        "com.features.order.ordercheckout.ui.activity.OrderCheckoutActivity"
    private const val ACTION_OPEN_CONFIRMATION_ORDER =
        "com.features.order.ordercheckout.ui.activity.ConfirmationOrderActivity"

    enum class Extras {
        LOGIN_TOKEN,
        PRODUCT_FILTERED
    }

    fun openLogin(
        context: Context
    ) {
        context.startActivity(internalIntent(context, ACTION_LOGIN))
    }

    fun openLoginFromOrderList(
        context: Context
    ) {
        context.startActivity(
            internalIntent(context, ACTION_LOGIN)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    fun openOrderList(
        context: Context,
        token: String
    ) {
        context.startActivity(
            internalIntent(context, ACTION_ORDER_LIST)
                .putExtra(Extras.LOGIN_TOKEN.name, token)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    fun openOrderCheckout(
        context: Context,
        filtered: Array<OrderListResponse>
    ) {
        context.startActivity(
            internalIntent(context, ACTION_ORDER_CHECKOUT)
                .putExtra(Extras.PRODUCT_FILTERED.name, filtered)
        )
    }

    fun openConfirmationOrder(
        context: Context
    ) {
        context.startActivity(
            internalIntent(context, ACTION_OPEN_CONFIRMATION_ORDER)
        )
    }

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}