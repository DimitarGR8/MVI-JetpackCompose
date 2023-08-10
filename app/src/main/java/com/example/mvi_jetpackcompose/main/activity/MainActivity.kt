package com.example.mvi_jetpackcompose.main.activity

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mvi_jetpackcompose.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.mvi_jetpackcompose.core.presentation.composables.scaffold.PrimaryScaffold
import com.example.mvi_jetpackcompose.core.presentation.composables.navigationDrawer.PrimaryNavigationDrawer
import com.example.mvi_jetpackcompose.core.presentation.theme.JetpackComposeTemplateTheme
import com.example.mvi_jetpackcompose.core.util.events.EventBusViewModel
import com.example.mvi_jetpackcompose.custom.composables.dialogs.PermissionDialog
import com.example.mvi_jetpackcompose.custom.ext.drawUnderStatusBar
import com.example.mvi_jetpackcompose.custom.ext.openAppSettings
import com.example.mvi_jetpackcompose.custom.permissions.CameraPermissionTextProvider
import com.example.mvi_jetpackcompose.custom.permissions.LocationPermissionTextProvider
import com.ramcosta.composedestinations.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val eventBusViewModel: EventBusViewModel by viewModels()

    private val permissionsToRequest = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        window.drawUnderStatusBar()

        setContent {
            var isDarkMode by remember { mutableStateOf(false) }
            JetpackComposeTemplateTheme(isDarkMode) {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                // Status bar color
                SetStatusBarColor(isDarkMode)

                // Permissions
                PermissionsToRequest()

                AppEventsHandler(navController = navController, drawerState = drawerState) {
                    isDarkMode = it
                }

                PrimaryNavigationDrawer(
                    drawerState = drawerState,
                    icon = R.drawable.local_offer,
                    userIcon = "https://st3.depositphotos.com/4111759/13425/v/600/depositphotos_134255532-stock-illustration-profile-placeholder-male-default-profile.jpg",
                    userName = "John Doe",
                    onProfileClick = { /*TODO*/ },
                    buttons = listOf("asd", "asd2"),
                    onListItemClicked = {}
                ) {
                    PrimaryScaffold(
                        navController = navController
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AppEventsHandler(
        navController: NavHostController,
        drawerState: DrawerState,
        switchDarkMode: (Boolean) -> Unit,
    ) {
        val scope = rememberCoroutineScope()
        // Events
        val events = eventBusViewModel.events.collectAsState()
        events.value.displayToastMessageEvent.peekValue {
            Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
        }
        events.value.navigationEvent.peekValue {
            it?.direction?.let { direction ->
                navController.navigate(direction) {
                    if (!it.addToBackstack) {
                        this.popUpTo(navController.currentBackStackEntry?.destination?.route.toString()) {
                            inclusive = true
                        }
                    }
                }
            }
        }
        events.value.drawerInterAction.peekValue { it ->
            it?.drawerState?.let {
                if (it) {
                    scope.launch {
                        drawerState.open()
                    }
                } else {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        }
        events.value.switchThemeEvent.peekValue {
            it?.isDarkTheme?.let { isDark ->
                switchDarkMode.invoke(isDark)
            }
        }
    }

    @Composable
    private fun SetStatusBarColor(isDarkTheme: Boolean) {
        val systemUiController = rememberSystemUiController()
        systemUiController.apply {
            setStatusBarColor(color = Color.Transparent, darkIcons = !isDarkTheme)
            setNavigationBarColor(color = MaterialTheme.colorScheme.background)
        }
    }

    @Composable
    private fun PermissionsToRequest() {
        val dialogQueue = mainViewModel.visiblePermissionsDialogQueue

        val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { perms ->
                permissionsToRequest.forEach { permission ->
                    mainViewModel.onPermissionResult(
                        permission = permission,
                        isGranted = perms[permission] == true
                    )
                }
            }
        )

        // Permissions
        dialogQueue.reversed().forEach { permission ->
            PermissionDialog(
                permission = when (permission) {
                    Manifest.permission.CAMERA -> CameraPermissionTextProvider()
                    Manifest.permission.ACCESS_COARSE_LOCATION -> LocationPermissionTextProvider()
                    else -> return@forEach
                },
                isPermanentlyDeclined = !shouldShowRequestPermissionRationale(permission),
                onDismiss = mainViewModel::dismissPermissionDialog,
                onOkClicked = {
                    mainViewModel.dismissPermissionDialog()
                    multiplePermissionResultLauncher.launch(arrayOf(permission))
                },
                onGoToAppSettingsClicked = { openAppSettings() }
            )
        }

        SideEffect {
            multiplePermissionResultLauncher.launch(permissionsToRequest)
        }
    }
}

