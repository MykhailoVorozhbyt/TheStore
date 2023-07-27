package com.example.core.base.states

abstract class BaseState {
    abstract val errorState: ErrorState
    abstract val isLoading: Boolean
}