package com.example.mvi_jetpackcompose.custom.ext

import androidx.lifecycle.ViewModel

fun ViewModel.tag(): String = this::class.java.simpleName
