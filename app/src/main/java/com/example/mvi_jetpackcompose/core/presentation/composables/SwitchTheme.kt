package com.example.mvi_jetpackcompose.core.presentation.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable

@Composable
fun SwitchTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onThemeChanged: (Boolean) -> Unit,
) {
    Switch(
        checked = isDarkTheme,
        onCheckedChange = {
            onThemeChanged(it)
        }
    )
}