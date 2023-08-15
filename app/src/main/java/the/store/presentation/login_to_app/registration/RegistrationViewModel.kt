package the.store.presentation.login_to_app.registration

import com.example.core.base.vm.BaseStateViewModel
import javax.inject.Inject

class RegistrationViewModel @Inject constructor() :
    BaseStateViewModel<RegistrationUiState, RegistrationUiEvent>(RegistrationUiState()) {

    override fun onTriggerEvent(eventType: RegistrationUiEvent) {
        when (eventType) {
            else -> {}
        }
    }

}