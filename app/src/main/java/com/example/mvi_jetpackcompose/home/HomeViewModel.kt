package com.example.mvi_jetpackcompose.home

import com.example.mvi_jetpackcompose.core.presentation.base.BaseViewModel
import com.example.mvi_jetpackcompose.domain.usecase.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBooks: GetBooksUseCase,
) : BaseViewModel<HomeAction, HomeState>() {
    override var _viewState = MutableStateFlow(HomeState())

    override fun onActionReceived(viewAction: HomeAction) {
        super.onActionReceived(viewAction)
        when(viewAction){
            is HomeAction.ChangeDrawerState ->{
//                _viewState.update { it.copy(isBottomSheetOpen = viewAction.isOpen) }
            }
            else->{

            }
        }
    }
}
