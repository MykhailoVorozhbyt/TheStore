package com.example.core.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseStateVM<STATE, EVENT>(state: STATE) : BaseViewModel() {
    private val _uiState = MutableStateFlow(state)
    val uiState = _uiState.asStateFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _uiState.emit(state)
    }

//    override fun startLoading() {
//        super.startLoading()
//        _uiState.value = BaseViewState.Loading
//    }
//
//    override fun handleError(exception: Throwable) {
//        super.handleError(exception)
//        _uiState.value = BaseViewState.Error(exception)
//    }
}
