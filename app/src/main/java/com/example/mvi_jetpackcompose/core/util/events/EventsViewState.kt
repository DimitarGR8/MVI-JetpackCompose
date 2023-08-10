package com.example.mvi_jetpackcompose.core.util.events

import com.example.mvi_jetpackcompose.core.util.SingleUseValue

data class EventsViewState(
    val displayToastMessageEvent: SingleUseValue<Event.DisplayToastMessage?> = SingleUseValue(null),
    val navigationEvent: SingleUseValue<Event.Navigation?> = SingleUseValue(null),
    val drawerInterAction: SingleUseValue<Event.DrawerStateAction?> = SingleUseValue(null),
    val switchThemeEvent:SingleUseValue<Event.SwitchTheme?> = SingleUseValue(null)
)