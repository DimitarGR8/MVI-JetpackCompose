package com.example.mvi_jetpackcompose.core.presentation.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_jetpackcompose.core.data.remote.ErrorResponse
import com.example.mvi_jetpackcompose.core.data.remote.GeneralException
import com.example.mvi_jetpackcompose.core.data.remote.ResponseException
import com.example.mvi_jetpackcompose.core.data.remote.UnknownError
import com.example.mvi_jetpackcompose.custom.ext.log
import com.example.mvi_jetpackcompose.custom.ext.logMessage
import com.example.mvi_jetpackcompose.core.util.events.Event
import com.example.mvi_jetpackcompose.core.util.events.IEventBus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseViewModel<ViewAction : BaseAction<ViewState>, ViewState> : ViewModel() {
    abstract val viewState: StateFlow<ViewState>
    private val actionSubject: MutableSharedFlow<ViewAction> = MutableSharedFlow()
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.logMessage.log("view model exception")
    }

    @Inject
    lateinit var eventBus: IEventBus

    init {
        actionSubject
            .onEach(::processAction)
            .launchIn(viewModelScope)
    }

    @CallSuper
    protected open fun onActionReceived(viewAction: ViewAction) {
        if (viewAction is Event) {
            eventBus.produceEvent(viewAction)
        }
    }

    fun execute(
        coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default,
        call: suspend () -> Unit
    ) {
        viewModelScope.launch(coroutineDispatcher + exceptionHandler) {
            call()
        }
    }

    fun postAction(action: ViewAction) {
        viewModelScope.launch {
            actionSubject.emit(action)
        }
    }

    private fun processAction(viewAction: ViewAction) {
        onActionReceived(viewAction)
    }

    protected suspend fun <Result> handleNetworkCall(
        call: suspend () -> Result,
        onSuccess: suspend (Result) -> Unit,
        onError: suspend (Error: ErrorResponse?) -> Unit = {}
    ) {
        try {
            onSuccess(call())
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            when (e) {
                is ResponseException -> onError(e.error)
                is GeneralException.UnknownException -> onError(UnknownError(e.code))
                is GeneralException -> {
                    // TODO: handle general error
                    onError(null)
                }
            }
        }
    }
}
