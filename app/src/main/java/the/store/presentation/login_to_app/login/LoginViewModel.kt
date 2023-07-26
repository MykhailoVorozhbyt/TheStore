package the.store.presentation.login_to_app.login

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseStateVM
import com.example.core.base.states.ErrorState
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.isPhoneCorrectLength
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
            LoginState(
                phoneValue = phone,
                errorState = checkPhone(phone)
            )
        )
    }

    private fun submitPasswordChangedEvent(phone: String) {
        setState(
            LoginState(
                phoneValue = phone,
                errorState = checkPassword(phone)
            )
        )
    }

    private fun submitSubmitLoginClickEvent() {
        val inputsValidated = validateInputs()
        if (inputsValidated) {
            // TODO Trigger login in authentication flow
//            loginState.value = loginState.value.copy(isLoginSuccessful = true)
        }
    }

    fun getWorkerByPhoneAndPassword(phone: String, password: String) {
        startLoading()
        viewModelScope.launch {
            try {
                val result = workerRepository.getWorkerByPhoneAndPassword(
                    password,
                    phone
                )
                if (result == null) {
                } else {

                }
            } catch (e: Exception) {

            }
        }
    }

    private fun checkPhone(phone: String): LoginErrorState {
        if (phone.isBlank()) {
            return LoginErrorState(phoneEmptyErrorState)
        }
        if (isPhoneCorrectLength(phone).not()) {
            return LoginErrorState(
                phoneErrorState = ErrorState(
                    hasError = true,
                    errorMessageStringResource = com.example.theme.R.string.incorrect_phone_error
                )
            )
        }
        return LoginErrorState()
    }

    private fun checkPassword(password: String): LoginErrorState {
        if (password.isBlank()) {
            return LoginErrorState(passwordErrorState = passwordEmptyErrorState)
        }
        return LoginErrorState()
    }

    private fun validateInputs(): Boolean {
        val phoneValidate: LoginErrorState = checkPhone(uiState.value.phoneValue)
        val passwordValidate: LoginErrorState = checkPassword(uiState.value.passwordValue)
        return when {
            phoneValidate.phoneErrorState.hasError.not() && passwordValidate.passwordErrorState.hasError.not() -> {
                true
            }
            phoneValidate.phoneErrorState.hasError -> {
                setState(LoginState(errorState = phoneValidate))
                false
            }
            passwordValidate.passwordErrorState.hasError -> {
                setState(LoginState(errorState = passwordValidate))
                false
            }
            else -> {
                setState(LoginState(errorState = LoginErrorState()))
                true
            }
        }
    }


}