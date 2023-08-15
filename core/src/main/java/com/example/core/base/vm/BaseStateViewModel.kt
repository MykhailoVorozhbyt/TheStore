package com.example.core.base.vm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseStateViewModel<STATE, EVENT>(initialState: STATE) : BaseViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    fun setState(newState: STATE) = safeLaunch {
        _uiState.value = newState
    }

}
