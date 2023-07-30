package the.store.presentation.login_to_app.registration

data class RegistrationContract(
    val isAllInputsDataCorrect: Boolean = false
)

sealed class RegistrationUiEvent {
    object SubmitLoginClick : RegistrationUiEvent()
}