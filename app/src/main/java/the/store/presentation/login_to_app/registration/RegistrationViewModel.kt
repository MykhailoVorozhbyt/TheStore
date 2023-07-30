package the.store.presentation.login_to_app.registration

import com.example.core.base.BaseStateVM
import javax.inject.Inject

class RegistrationViewModel @Inject constructor() :
    BaseStateVM<RegistrationContract, RegistrationUiEvent>(RegistrationContract()) {

    override fun onTriggerEvent(eventType: RegistrationUiEvent) {
        when (eventType) {
            else -> {}
        }
    }

}