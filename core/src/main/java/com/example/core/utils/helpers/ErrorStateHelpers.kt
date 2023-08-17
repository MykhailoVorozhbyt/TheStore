package com.example.core.utils.helpers

import com.example.core.base.states.FieldErrorState
import com.example.theme.R

val phoneEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_phone
)

val passwordEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)