package the.store.presentation.login_to_app.registration

import com.example.core.base.BaseStateViewModel
import javax.inject.Inject

class RegistrationViewModel @Inject constructor() :
    BaseStateViewModel<RegistrationContract, RegistrationUiEvent>(RegistrationContract()) {

    override fun onTriggerEvent(eventType: RegistrationUiEvent) {
        when (eventType) {
            else -> {}
        }
    }

}