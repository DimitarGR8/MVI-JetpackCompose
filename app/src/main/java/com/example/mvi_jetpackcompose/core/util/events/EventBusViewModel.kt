package com.example.mvi_jetpackcompose.core.util.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_jetpackcompose.core.util.SingleUseValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class EventBusViewModel @Inject constructor(
    eventBus: IEventBus
) : ViewModel() {

    private val _events = MutableStateFlow(EventsViewState())
    val events = _events.asStateFlow()

    init {
        eventBus.events.onEach { event ->
            when (event) {
                is Event.DisplayToastMessage -> {
                    _events.update {
                        it.copy(displayToastMessageEvent = SingleUseValue(event))
                    }
                }
                is Event.Navigation -> {
                    _events.update {
                        it.copy(navigationEvent = SingleUseValue(event))
                    }
                }
                is Event.DrawerStateAction->{
                    _events.update {
                        it.copy(drawerInterAction = SingleUseValue(event))
                    }
                }

                is Event.SwitchTheme -> {
                    _events.update {
                        it.copy(switchThemeEvent = SingleUseValue(event))
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}