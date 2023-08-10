package com.example.mvi_jetpackcompose.custom.ext

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Int.pxToDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).toInt().dp
}

fun Resources.getStatusBarHeight(): Dp {
    val resourceId = getIdentifier("status_bar_height", "dimen", "android")
    val statusBarHeight = if (resourceId > 0) {
        getDimensionPixelSize(resourceId)
    } else {
        0
    }
    return statusBarHeight.pxToDp()
}

fun Resources.getNavigationBarHeight(): Dp {
    val resourceId = getIdentifier("navigation_bar_height", "dimen", "android")
    val statusBarHeight = if (resourceId > 0) {
        getDimensionPixelSize(resourceId)
    } else {
        0
    }
    return statusBarHeight.pxToDp()
}
