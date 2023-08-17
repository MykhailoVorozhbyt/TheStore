package the.store.presentation.login_to_app.registration.models

data class RegistrationUiState(
    var name: String = "",
    var surname: String = "",
    var phone: String = "",
    var password: String = "",
    var inputDataErrorState: RegistrationErrorState = RegistrationErrorState()
)

