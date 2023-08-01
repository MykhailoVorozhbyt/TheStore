package the.store.presentation.login_to_app.registration

import com.example.core.base.states.BaseState
import com.example.core.base.states.ErrorState

data class RegistrationContract(
    val isAllInputsDataCorrect: Boolean = false,
    override val errorState: ErrorState = ErrorState(),
    override val isLoading: Boolean = false
) : BaseState()

sealed class RegistrationUiEvent {
    object SubmitLoginClick : RegistrationUiEvent()
}