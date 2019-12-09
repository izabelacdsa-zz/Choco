package com.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, work: (T) -> Unit): Observer<T> {

    val observer: Observer<T> = createObserverNotNull(work)
    observe(owner, observer)
    return observer
}

fun <T> createObserverNotNull(work: (T) -> Unit): Observer<T> {
    return Observer { value -> value?.let { work(it) } }
}