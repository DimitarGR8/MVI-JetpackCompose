package com.example.mvi_jetpackcompose.main.activity

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val visiblePermissionsDialogQueue = mutableStateListOf<String>()

    fun dismissPermissionDialog() = visiblePermissionsDialogQueue.removeFirst()

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted && !visiblePermissionsDialogQueue.contains(permission)) {
            visiblePermissionsDialogQueue.add(permission)
        }
    }
}
