package com.example.mvi_jetpackcompose.custom.ext

import android.view.Window
import androidx.core.view.WindowCompat

fun Window.drawUnderStatusBar() {
    WindowCompat.setDecorFitsSystemWindows(this, false)
}
