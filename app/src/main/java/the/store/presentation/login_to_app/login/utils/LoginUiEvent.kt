package the.store.presentation.login_to_app.login.utils

sealed class LoginUiEvent {
    object InitView : LoginUiEvent()
    data class PhoneChanged(val inputValue: String) : LoginUiEvent()
    data class PasswordChanged(val inputValue: String) : LoginUiEvent()
    object SubmitLoginClick : LoginUiEvent()
}