package the.store.presentation.login_to_app.login.models

sealed class LoginUiEvent {
    data class PhoneChanged(val inputValue: String) : LoginUiEvent()
    data class PasswordChanged(val inputValue: String) : LoginUiEvent()
    data object SubmitLoginClick : LoginUiEvent()
    data object DeleteAllStateExceptData : LoginUiEvent()
}