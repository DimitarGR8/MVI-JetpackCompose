package com.example.mvi_jetpackcompose.core.presentation.base

interface BaseAction<Data> {
    fun updateData(previousData: Data): Data = previousData
}
