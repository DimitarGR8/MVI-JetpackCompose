package com.example.mvi_jetpackcompose.custom.ext

import android.content.Context
import androidx.compose.ui.unit.Dp

fun Dp.toRealDp(context: Context): Dp {
    return this / context.resources.displayMetrics.density
}

