package com.example.core.base.states

sealed interface BaseViewState<out T> {
    data object Loading : BaseViewState<Nothing>
    data object Empty : BaseViewState<Nothing>
    data class Data<T>(val value: T) : BaseViewState<T>
    data class Error(val throwable: Throwable) : BaseViewState<Nothing>
}