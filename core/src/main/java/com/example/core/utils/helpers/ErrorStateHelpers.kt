package com.example.core.utils.helpers

import com.example.core.base.states.FieldErrorState
import com.example.theme.R

val emptyFieldErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.field_cannot_be_empty
)

val phoneEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_phone
)

val passwordEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)

val nameEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)
val surnameEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)

val passwordLengthErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.password_length_must_be_more_than_4_characters
)