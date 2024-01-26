package com.example.core.utils.helpers

import com.example.core.base.states.FieldErrorState
import com.example.theme.R

val emptyFieldErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.field_cannot_be_empty
)

/**
 * PHONE ERROR STATE
 * */

val phoneEmptyErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.login_error_msg_empty_phone
)

val incorrectPhoneErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.incorrect_phone_error
)

/**
 * PASSWORD ERROR STATE
 * */

val passwordEmptyErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.login_error_msg_empty_password
)

val passwordLengthErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.password_length_must_be_more_than_4_characters
)

/**
 * NAME ERROR STATE
 * */

val nameEmptyErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.login_error_msg_empty_name
)

/**
 * SURNAME ERROR STATE
 * */


val surnameEmptyErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.login_error_msg_empty_surname
)

/**
 * EMAIL ERROR STATE
 * */


val emailEmptyErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.login_error_msg_empty_email
)

val invalidateEmailAddressErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.invalidate_email_address
)

/**
 * PRODUCT PRICE ERROR STATE
 * */

val invalidatePriceErrorState = FieldErrorState(
    hasError = true,
    errorStringRes = R.string.the_price_must_be_greater_than_zero
)
