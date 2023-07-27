package com.example.core.base.states

import androidx.annotation.StringRes

data class FieldErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = com.example.theme.R.string.empty_string
)