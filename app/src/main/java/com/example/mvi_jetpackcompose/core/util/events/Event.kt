package com.example.mvi_jetpackcompose.core.util.events

import com.ramcosta.composedestinations.spec.Direction

sealed interface Event {

    interface Navigation : Event {
        val direction: Direction
        val addToBackstack: Boolean
    }
    interface SwitchTheme : Event {
        val isDarkTheme: Boolean
    }

    interface DisplayToastMessage : Event {
        val message: String
    }

    interface DrawerStateAction : Event{
        val drawerState: Boolean
    }
}