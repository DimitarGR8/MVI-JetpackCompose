package com.example.mvi_jetpackcompose.custom.composables.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerBottomNav() {
    Spacer(modifier = Modifier.fillMaxWidth().height(55.dp))
}
