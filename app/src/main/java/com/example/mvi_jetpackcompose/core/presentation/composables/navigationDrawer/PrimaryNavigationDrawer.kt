package com.example.mvi_jetpackcompose.core.presentation.composables.navigationDrawer

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.mvi_jetpackcompose.R
import com.example.mvi_jetpackcompose.custom.composables.button.DrawerButton
import com.example.mvi_jetpackcompose.custom.composables.row.UserRow

@Composable
fun PrimaryNavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    @DrawableRes icon: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    userIcon: String,
    userName: String,
    onProfileClick: () -> Unit,
    buttons: List<String>,
    buttonBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    buttonTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    onListItemClicked: (id: String) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        gesturesEnabled = drawerState.isOpen,
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawerContent(
                modifier = modifier.fillMaxSize(),
                icon = icon,
                buttons = buttons,
                userName = userName,
                onProfileClick = onProfileClick,
                userIcon = userIcon,
                onListItemClicked = onListItemClicked,
                buttonBackgroundColor = buttonBackgroundColor,
                buttonTextColor = buttonTextColor,
                backgroundColor = backgroundColor
            )
        },
        content = content
    )
}

@Composable
fun NavigationDrawerContent(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    userIcon: String,
    userName: String,
    onProfileClick: () -> Unit,
    buttons: List<String>,
    buttonBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    buttonTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    onListItemClicked: (id: String) -> Unit
) {
    ModalDrawerSheet {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(dimensionResource(id = R.dimen.default_padding))
                .background(backgroundColor)
        ) {
            Image(painter = painterResource(id = icon), contentDescription = null)
            Column(modifier = Modifier.weight(1f)) {
                buttons.forEach {
                    DrawerButton(
                        text = it,
                        buttonTextColor = buttonTextColor,
                        buttonBackgroundColor = buttonBackgroundColor
                    ) {
                        onListItemClicked(it)
                    }
                }
            }
            UserRow(userIcon = userIcon, userName = userName, onProfileClick = onProfileClick)
        }
    }
}