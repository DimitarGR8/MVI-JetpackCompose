package com.example.mvi_jetpackcompose.custom.permissions

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}
