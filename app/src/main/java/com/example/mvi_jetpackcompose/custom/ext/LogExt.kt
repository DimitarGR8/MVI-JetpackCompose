package com.example.mvi_jetpackcompose.custom.ext

import android.util.Log
import androidx.lifecycle.ViewModel

fun <T> T.log(key: String = "MVI"): T {
    Log.e(key, toString())
    return this
}

val Throwable.logMessage: String get() = (message ?: "") + "\n" + stackTraceToString()

fun ViewModel.logAction(action: Any) =
    "${this::class.java.simpleName} - Action - ${action.javaClass.simpleName}"
