package the.store.presentation.login_to_app.login

import com.example.core.base.states.BaseState
import com.example.core.base.states.ErrorState
import com.example.core.base.states.FieldErrorState

data class LoginState(
    var userLoggedIn: Boolean = false,
    var userNotLoggedIn: Boolean = false,
    var phoneValue: String = "",
    var passwordValue: String = "",
    var inputDataErrorState: LoginErrorState = LoginErrorState(),
    override val errorState: ErrorState = ErrorState(),
    override val isLoading: Boolean = false,
) : BaseState()

data class LoginErrorState(
    val phoneErrorState: FieldErrorState = FieldErrorState(),
    val passwordErrorState: FieldErrorState = FieldErrorState()
)

val phoneEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = com.example.theme.R.string.login_error_msg_empty_phone
)

val passwordEmptyErrorState = FieldErrorState(
    hasError = true,
    errorMessageStringResource = com.example.theme.R.string.login_error_msg_empty_password
)

sealed class LoginUiEvent {
    data class PhoneChanged(val inputValue: String) : LoginUiEvent()
    data class PasswordChanged(val inputValue: String) : LoginUiEvent()
    object SubmitLoginClick : LoginUiEvent()
}