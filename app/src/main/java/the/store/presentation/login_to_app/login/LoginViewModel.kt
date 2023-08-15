package the.store.presentation.login_to_app.login

import com.example.core.base.states.FieldErrorState
import com.example.core.base.vm.BaseStateViewModel
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppDispatchers
import com.example.core.utils.AppLogger
import com.example.core.utils.isValidUkrainianPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import the.store.presentation.login_to_app.login.utils.LoginErrorState
import the.store.presentation.login_to_app.login.utils.LoginUiEvent
import the.store.presentation.login_to_app.login.utils.LoginUiState
import the.store.presentation.login_to_app.login.utils.passwordEmptyErrorState
import the.store.presentation.login_to_app.login.utils.phoneEmptyErrorState
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
        }
    }

    private fun submitPhoneChangedEvent(phone: String) {
        AppLogger.log("submitPhoneChangedEvent")
        setState(
            uiState.value.copy(
                phoneValue = phone, inputDataErrorState = checkPhone(phone)
            )
        )
    }

    private fun submitPasswordChangedEvent(password: String) {
        AppLogger.log("submitPasswordChangedEvent")
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

    override fun startLoading() {
        super.startLoading()
        AppLogger.log("startLoading")

        setState(uiState.value.copy(isLoading = true))
    }

    private fun getWorkerByPhoneAndPassword() {
        safeLaunch(dispatchers.io) {
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
                AppLogger.log("getWorkerByPhoneAndPassword $result")

            } catch (e: Exception) {
                AppLogger.log(e)
                handleError(e)
            }
        }
    }

    private fun checkPhone(phone: String): LoginErrorState {
        if (phone.isBlank()) {
            return LoginErrorState(phoneEmptyErrorState)
        }
        if (isValidUkrainianPhoneNumber(phone).not()) {
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