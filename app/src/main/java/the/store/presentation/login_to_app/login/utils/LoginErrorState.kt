package the.store.presentation.login_to_app.login.utils

import com.example.core.base.states.FieldErrorState
import com.example.theme.R

data class LoginErrorState(
    val phoneErrorState: FieldErrorState = FieldErrorState(),
    val passwordErrorState: FieldErrorState = FieldErrorState()
)

val phoneEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_phone
)

val passwordEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)