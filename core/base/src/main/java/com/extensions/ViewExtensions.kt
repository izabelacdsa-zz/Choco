package com.extensions

import android.view.View

fun View.showView(hasToShow: Boolean) {
    visibility = if (hasToShow) View.VISIBLE else View.GONE
}

inline var View.notVisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }