package com.example.mvi_jetpackcompose.core.util.events

import com.example.mvi_jetpackcompose.core.util.events.Event
import kotlinx.coroutines.flow.Flow

interface IEventBus {

    val events: Flow<Event>

    fun produceEvent(event: Event)

}