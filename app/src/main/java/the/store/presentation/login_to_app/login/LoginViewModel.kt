package the.store.presentation.login_to_app.login

import com.example.core.base.BaseStateVM
import com.example.core.base.states.ErrorState
import com.example.core.base.states.FieldErrorState
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppLogger
import com.example.core.utils.isPhoneCorrectLength
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val workerRepository: WorkerRepository
) : BaseStateVM<LoginState, LoginUiEvent>(LoginState()) {


    override fun onTriggerEvent(eventType: LoginUiEvent) {
        when (eventType) {
            is LoginUiEvent.PhoneChanged -> submitPhoneChangedEvent(eventType.inputValue)
            is LoginUiEvent.PasswordChanged -> submitPasswordChangedEvent(eventType.inputValue)
            is LoginUiEvent.SubmitLoginClick -> submitSubmitLoginClickEvent()
        }
    }

    private fun submitPhoneChangedEvent(phone: String) {
        setState(
            uiState.value.copy(
                phoneValue = phone,
                inputDataErrorState = checkPhone(phone)
            )
        )
    }

    private fun submitPasswordChangedEvent(password: String) {
        setState(
            uiState.value.copy(
                passwordValue = password,
                inputDataErrorState = checkPassword(password)
            )
        )
    }

    private fun submitSubmitLoginClickEvent() {
        val inputsValidated = validateInputs()
        if (inputsValidated) {
            getWorkerByPhoneAndPassword()
        }
    }

    private fun getWorkerByPhoneAndPassword() {
        setState(uiState.value.copy(isLoading = true))
        safeLaunch {
            try {
                val result = workerRepository.getWorkerByPhoneAndPassword(
                    uiState.value.phoneValue,
                    uiState.value.passwordValue
                )
                if (result == null) {
                    setState(uiState.value.copy(userNotLoggedIn = true))
                } else {
                    setState(uiState.value.copy(userLoggedIn = true))
                }
            } catch (e: Exception) {
                AppLogger.log(e)
                setState(uiState.value.copy(errorState = ErrorState(e.localizedMessage)))
            }
        }

    }

    private fun checkPhone(phone: String): LoginErrorState {
        if (phone.isBlank()) {
            return LoginErrorState(phoneEmptyErrorState)
        }
        if (isPhoneCorrectLength(phone).not()) {
            return LoginErrorState(
                phoneErrorState = FieldErrorState(
                    hasError = true,
                    errorMessageStringResource = com.example.theme.R.string.incorrect_phone_error
                )
            )
        }
        return LoginErrorState()
    }

    private fun checkPassword(password: String): LoginErrorState {
        return if (password.isBlank()) LoginErrorState(passwordErrorState = passwordEmptyErrorState) else LoginErrorState()
    }

    private fun validateInputs(): Boolean {
        val phoneValidate: LoginErrorState = checkPhone(uiState.value.phoneValue)
        val passwordValidate: LoginErrorState = checkPassword(uiState.value.passwordValue)
        return when {
            phoneValidate.phoneErrorState.hasError -> {
                setState(uiState.value.copy(inputDataErrorState = phoneValidate))
                false
            }
            passwordValidate.passwordErrorState.hasError -> {
                setState(uiState.value.copy(inputDataErrorState = passwordValidate))
                false
            }
            else -> {
                setState(uiState.value.copy(inputDataErrorState = LoginErrorState()))
                true
            }
        }
    }


}