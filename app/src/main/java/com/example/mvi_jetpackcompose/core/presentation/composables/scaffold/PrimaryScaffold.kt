package com.example.mvi_jetpackcompose.core.presentation.composables.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mvi_jetpackcompose.home.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun PrimaryScaffold(
    navController: NavHostController,
) {
    Scaffold(
        content = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(bottom = it.calculateBottomPadding())
            ) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController
                )
            }
        }
    )
}
