package the.store.presentation.login_to_app.login

import com.example.core.base.MviViewModel
import com.example.core.base.states.BaseViewState
import com.example.core.base.states.FieldErrorState
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppDispatchers
import com.example.core.utils.AppLogger
import com.example.core.utils.isValidUkrainianPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
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
) : MviViewModel<BaseViewState<LoginUiState>, LoginUiEvent>() {

    override fun onTriggerEvent(eventType: LoginUiEvent) {
        when (eventType) {
            is LoginUiEvent.PhoneChanged -> submitPhoneChangedEvent(eventType.inputValue)
            is LoginUiEvent.PasswordChanged -> submitPasswordChangedEvent(eventType.inputValue)
            is LoginUiEvent.SubmitLoginClick -> submitSubmitLoginClickEvent()
            is LoginUiEvent.InitView -> submitInitViewEvent()
        }
    }

    private fun setUiState(state: LoginUiState) {
        setState(BaseViewState.Data(state))
    }

    private fun submitInitViewEvent() {
        setState(BaseViewState.Data(LoginUiState()))
    }

    private fun submitPhoneChangedEvent(phone: String) {
        safeLaunch {
            val castState =
                uiState.filterIsInstance<BaseViewState.Data<LoginUiState>>().map { it.value }
                    .first().copy(
                        phoneValue = phone,
                        inputDataErrorState = checkPhone(phone)
                    )
            setUiState(castState)
        }
    }

    private fun submitPasswordChangedEvent(password: String) {
        safeLaunch {
            val castState =
                uiState.filterIsInstance<BaseViewState.Data<LoginUiState>>()
                    .map { it.value }
                    .first()
                    .copy(
                        passwordValue = password,
                        inputDataErrorState = checkPassword(password)
                    )
            setUiState(castState)
        }
    }

    private fun submitSubmitLoginClickEvent() {
        safeLaunch {
            val inputsValidated = validateInputs()
            if (inputsValidated) {
                getWorkerByPhoneAndPassword()
            }
        }
    }

    private fun getWorkerByPhoneAndPassword() {
        startLoading()
        safeLaunch(dispatchers.io) {
            try {
                val castState =
                    uiState.filterIsInstance<BaseViewState.Data<LoginUiState>>().map { it.value }
                        .first()
                val result = workerRepository.getWorkerByPhoneAndPassword(
                    castState.phoneValue,
                    castState.passwordValue
                )
                if (result == null) {
                    setUiState(LoginUiState(userNotLoggedIn = true))
                } else {
                    setUiState(LoginUiState(userLoggedIn = true))
                }
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

    private suspend fun validateInputs(): Boolean {
        val castState =
            uiState.filterIsInstance<BaseViewState.Data<LoginUiState>>().map { it.value }.first()
        val phoneValidate: LoginErrorState = checkPhone(castState.phoneValue)
        val passwordValidate: LoginErrorState = checkPassword(castState.passwordValue)
        return when {
            phoneValidate.phoneErrorState.hasError -> {
                setUiState(LoginUiState(inputDataErrorState = phoneValidate))
                false
            }
            passwordValidate.passwordErrorState.hasError -> {
                setUiState(LoginUiState(inputDataErrorState = passwordValidate))
                false
            }
            else -> {
                setUiState(LoginUiState(inputDataErrorState = LoginErrorState()))
                true
            }
        }
    }


}