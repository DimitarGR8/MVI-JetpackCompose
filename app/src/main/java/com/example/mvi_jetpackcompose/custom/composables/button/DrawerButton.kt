package com.example.mvi_jetpackcompose.custom.composables.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DrawerButton(
    text: String,
    buttonBackgroundColor: Color,
    buttonTextColor: Color,
    onClick: () -> Unit,

    ) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(buttonBackgroundColor),
        color = buttonTextColor,
        text = text
    )
}