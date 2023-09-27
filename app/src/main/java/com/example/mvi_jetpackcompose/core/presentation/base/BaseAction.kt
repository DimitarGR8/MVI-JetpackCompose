package com.example.mvi_jetpackcompose.core.presentation.base

import kotlinx.coroutines.flow.MutableStateFlow

interface BaseAction<Data> {
    fun updateData(previousData: MutableStateFlow<Data>) = Unit
}
