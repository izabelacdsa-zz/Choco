package com.extensions

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.base.R

fun Activity.showErrorDialog(hasError: HasError) {

    val dialog = AlertDialog.Builder(this).run {
        val customLayout = layoutInflater.inflate(R.layout.dialog_default_error, null)
        hasError.drawableRes?.let {
            customLayout.findViewById<ImageView>(R.id.ivError).setImageResource(it)
        }
        hasError.descriptionRes?.let {
            customLayout.findViewById<TextView>(R.id.tvErrorDescription).setText(it)
        }
        setView(customLayout)
        create()
    }
    dialog.show()
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}