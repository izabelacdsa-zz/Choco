package com.actions

import android.content.Context
import android.content.Intent

object Actions {

    const val ACTION_LOGIN = "com.features.login.activity.LoginActivity"

    fun openLogin(
        context: Context
    ) {
        context.startActivity(internalIntent(context, ACTION_LOGIN))
    }

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}