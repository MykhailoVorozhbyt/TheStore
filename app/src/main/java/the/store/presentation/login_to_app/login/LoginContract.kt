package the.store.presentation.login_to_app.login

import com.example.core.base.states.ErrorState

data class LoginState(
    val userLoggedIn: Boolean = false,
    val userNotLoggedIn: Boolean = false,
    val phoneValue: String = "",
    val passwordValue: String = "",
    val errorState: LoginErrorState = LoginErrorState(),
)

data class LoginErrorState(
    val phoneErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)

val phoneEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = com.example.theme.R.string.login_error_msg_empty_phone
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = com.example.theme.R.string.login_error_msg_empty_password
)

sealed class LoginUiEvent {
    data class PhoneChanged(val inputValue: String) : LoginUiEvent()
    data class PasswordChanged(val inputValue: String) : LoginUiEvent()
    object SubmitLoginClick : LoginUiEvent()
}