package com.example.core.base.states

abstract class BaseUiState : BaseState() {
    abstract val errorState: ErrorState
    abstract val isLoading: Boolean
}