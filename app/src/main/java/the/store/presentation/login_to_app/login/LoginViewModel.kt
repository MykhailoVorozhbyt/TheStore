package the.store.presentation.login_to_app.login

import com.example.core.base.states.FieldErrorState
import com.example.core.base.vm.BaseStateViewModel
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppDispatchers
import com.example.core.utils.AppLogger
import com.example.core.utils.helpers.passwordEmptyErrorState
import com.example.core.utils.helpers.passwordLengthErrorState
import com.example.core.utils.helpers.phoneEmptyErrorState
import com.example.core.utils.isValidUkrainianPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import the.store.presentation.login_to_app.login.models.LoginErrorState
import the.store.presentation.login_to_app.login.models.LoginUiEvent
import the.store.presentation.login_to_app.login.models.LoginUiState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val workerRepository: WorkerRepository,
    private val dispatchers: AppDispatchers
) : BaseStateViewModel<LoginUiState, LoginUiEvent>(LoginUiState()) {

    override fun onTriggerEvent(eventType: LoginUiEvent) {
        when (eventType) {
            is LoginUiEvent.PhoneChanged -> submitPhoneChangedEvent(eventType.inputValue)
            is LoginUiEvent.PasswordChanged -> submitPasswordChangedEvent(eventType.inputValue)
            is LoginUiEvent.SubmitLoginClick -> submitSubmitLoginClickEvent()
            is LoginUiEvent.DeleteAllStateExceptData -> submitDeleteAllStateExceptData()
        }
    }

    private fun submitDeleteAllStateExceptData() {
        val currentState = uiState.value
        setState(
            LoginUiState(
                phoneValue = currentState.phoneValue,
                passwordValue = currentState.passwordValue,
            )
        )
    }

    private fun submitPhoneChangedEvent(phone: String) {
        AppLogger.log("submitPhoneChangedEvent")
        setState(
            uiState.value.copy(
                phoneValue = phone, inputDataErrorState = isPhoneValid(phone)
            )
        )
    }

    private fun submitPasswordChangedEvent(password: String) {
        AppLogger.log("submitPasswordChangedEvent")
        setState(
            uiState.value.copy(
                passwordValue = password,
                inputDataErrorState = isPasswordValid(password)
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
        safeLaunch(dispatchers.io) {
            try {
                val result = workerRepository.getWorkerByPhoneAndPassword(
                    uiState.value.phoneValue,
                    uiState.value.passwordValue
                )
                if (result == null) {
                    setState(uiState.value.copy(userNotLoggedIn = true, isLoading = false))
                } else {
                    setState(uiState.value.copy(userLoggedIn = true, isLoading = false))
                }
                AppLogger.log("getWorkerByPhoneAndPassword $result")

            } catch (e: Exception) {
                AppLogger.log(e)
                handleError(e)
            }
        }
    }

    private fun isPhoneValid(phone: String): LoginErrorState {
        if (phone.isBlank()) {
            return LoginErrorState(phoneEmptyErrorState)
        }
        if (isValidUkrainianPhoneNumber(phone).not()) {
            return LoginErrorState(
                phoneErrorState = FieldErrorState(
                    hasError = true,
                    errorStringRes = com.example.theme.R.string.incorrect_phone_error
                )
            )
        }
        return LoginErrorState()
    }

    private fun isPasswordValid(password: String): LoginErrorState {
        if (password.isBlank()) {
            return LoginErrorState(passwordErrorState = passwordEmptyErrorState)
        }
        if (password.length <= 4) {
            return LoginErrorState(passwordErrorState = passwordLengthErrorState)
        }
        return LoginErrorState()
    }

    private fun validateInputs(): Boolean {
        val phoneValidate: LoginErrorState = isPhoneValid(uiState.value.phoneValue)
        val passwordValidate: LoginErrorState = isPasswordValid(uiState.value.passwordValue)
        AppLogger.log("validateInputs")

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