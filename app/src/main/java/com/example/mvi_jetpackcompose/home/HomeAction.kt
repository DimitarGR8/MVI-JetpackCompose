package com.example.mvi_jetpackcompose.home

import com.example.mvi_jetpackcompose.core.util.events.Event
import com.example.mvi_jetpackcompose.core.presentation.base.BaseAction

sealed class HomeAction : BaseAction<HomeState> {
    object TriggerToastMessage : HomeAction(), Event.DisplayToastMessage {
        override val message: String = "Search"
    }

    class ChangeDrawerState(val isOpen: Boolean) : HomeAction(), Event.DrawerStateAction {
        override val drawerState: Boolean = isOpen
    }

    class SwitchTheme(override val isDarkTheme: Boolean) : HomeAction(), Event.SwitchTheme {

    }
}
