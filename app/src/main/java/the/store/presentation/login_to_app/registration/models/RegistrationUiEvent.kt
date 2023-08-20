package the.store.presentation.login_to_app.registration.models

sealed class RegistrationUiEvent {
    data class InitUiContent(
        val phone: String,
        val password: String
    ) : RegistrationUiEvent()

    data class PhoneChanged(val phone: String): RegistrationUiEvent()
    data class PasswordChanged(val password: String): RegistrationUiEvent()
    data class NameChanged(val name: String): RegistrationUiEvent()
    data class SurnameChanged(val surname: String): RegistrationUiEvent()

    object SubmitRegistrationClick : RegistrationUiEvent()
}