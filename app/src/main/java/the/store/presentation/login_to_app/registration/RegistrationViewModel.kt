package the.store.presentation.login_to_app.registration

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import the.store.presentation.login_to_app.registration.models.RegistrationUiEvent
import the.store.presentation.login_to_app.registration.models.RegistrationUiState
import javax.inject.Inject

class RegistrationViewModel @Inject constructor() :
    MviViewModel<BaseViewState<RegistrationUiState>, RegistrationUiEvent>() {

    override fun onTriggerEvent(eventType: RegistrationUiEvent) {
        when (eventType) {
            is RegistrationUiEvent.InitUiContent -> submitEvent(eventType)
            is RegistrationUiEvent.SubmitRegistrationClick -> submitEvent(eventType)
            is RegistrationUiEvent.ReloadContent -> submitEvent(eventType)
            is RegistrationUiEvent.PhoneChanged -> submitEvent(eventType)
            is RegistrationUiEvent.PasswordChanged -> submitEvent(eventType)
            is RegistrationUiEvent.NameChanged -> submitEvent(eventType)
            is RegistrationUiEvent.SurnameChanged -> submitEvent(eventType)
        }
    }

    private fun setState(state: RegistrationUiState) {
        setState(BaseViewState.Data(state))
    }

    private fun submitEvent(event: RegistrationUiEvent.InitUiContent) = safeLaunch {
        startLoading()
        setState(
            RegistrationUiState(
                password = event.password,
                phone = event.phone
            )
        )
    }
    private fun submitEvent(event: RegistrationUiEvent.SubmitRegistrationClick) {}
    private fun submitEvent(event: RegistrationUiEvent.ReloadContent) {}
    private fun submitEvent(event: RegistrationUiEvent.PhoneChanged) {}
    private fun submitEvent(event: RegistrationUiEvent.PasswordChanged) {}
    private fun submitEvent(event: RegistrationUiEvent.NameChanged) {}
    private fun submitEvent(event: RegistrationUiEvent.SurnameChanged) {}

}