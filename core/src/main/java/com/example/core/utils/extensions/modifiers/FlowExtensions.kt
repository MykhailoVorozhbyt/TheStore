package com.example.core.utils.extensions.modifiers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


inline fun <reified T> StateFlow<T>.toMutableState(): MutableState<T> {
    return mutableStateOf(this.value)
}

inline fun <reified T> StateFlow<T>.toMutableStateFlow(): MutableStateFlow<T> {
    return this as? MutableStateFlow<T> ?: MutableStateFlow(this.value)
}