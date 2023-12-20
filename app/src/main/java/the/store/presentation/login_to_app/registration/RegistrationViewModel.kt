package the.store.presentation.login_to_app.registration

import com.example.core.base.states.BaseViewState
import com.example.core.base.states.FieldErrorState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.WorkerRepository
import com.example.core.domain.models.db_entity.WorkerEntity
import com.example.core.utils.AppDispatchers
import com.example.core.utils.AppLogger
import com.example.core.utils.enums.PreferenceKey
import com.example.core.utils.helpers.PreferenceHelper
import com.example.core.utils.helpers.emptyFieldErrorState
import com.example.core.utils.helpers.passwordEmptyErrorState
import com.example.core.utils.helpers.passwordLengthErrorState
import com.example.core.utils.helpers.phoneEmptyErrorState
import com.example.core.utils.isValidUkrainianPhoneNumber
import com.example.theme.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import the.store.presentation.login_to_app.registration.models.RegistrationUiEvent
import the.store.presentation.login_to_app.registration.models.RegistrationUiState
import the.store.presentation.login_to_app.registration.models.WorkerErrorState
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val workerRepository: WorkerRepository,
    private val dispatchers: AppDispatchers,
    private val preferenceHelper: PreferenceHelper
) : MviViewModel<BaseViewState<RegistrationUiState>, RegistrationUiEvent>() {

    override fun onTriggerEvent(eventType: RegistrationUiEvent) {
        when (eventType) {
            is RegistrationUiEvent.InitUiContent -> submitEvent(eventType)
            is RegistrationUiEvent.SubmitRegistrationClick -> submitEvent(eventType)
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
                password = event.password, phone = event.phone
            )
        )
    }

    private fun submitEvent(event: RegistrationUiEvent.SubmitRegistrationClick) = safeLaunch {
        val inputsValidated = validateInputs()
        if (inputsValidated) {
            createWorker()
        }
    }

    private suspend fun validateInputs(): Boolean {
        val castState =
            uiState.filterIsInstance<BaseViewState.Data<RegistrationUiState>>().map { it.value }
                .first()

        val phoneValidate: WorkerErrorState = checkPhone(castState.phone)
        val passwordValidate: WorkerErrorState = checkPassword(castState.password)
        val nameValidate: WorkerErrorState = checkPassword(castState.name)
        val surnameValidate: WorkerErrorState = checkPassword(castState.surname)

        return when {
            phoneValidate.phoneErrorState.hasError -> {
                setState(
                    castState.copy(
                        inputDataErrorState = phoneValidate
                    )
                )
                false
            }

            passwordValidate.passwordErrorState.hasError -> {
                setState(
                    castState.copy(
                        inputDataErrorState = passwordValidate
                    )
                )
                false
            }

            nameValidate.nameErrorState.hasError -> {
                setState(
                    castState.copy(
                        inputDataErrorState = nameValidate
                    )
                )
                false
            }

            surnameValidate.surnameErrorState.hasError -> {
                setState(
                    castState.copy(
                        inputDataErrorState = surnameValidate
                    )
                )
                false
            }

            else -> {
                setState(
                    castState.copy(
                        inputDataErrorState = WorkerErrorState()
                    )
                )
                true
            }
        }
    }

    private fun createWorker() {
        safeLaunch(dispatchers.io) {
            try {
                val castState = uiState.filterIsInstance<BaseViewState.Data<RegistrationUiState>>()
                    .map { it.value }.first()
                val worker = workerRepository.insertWorker(
                    WorkerEntity(
                        name = castState.name,
                        surname = castState.surname,
                        password = castState.password,
                        phone = castState.phone,
                        isOwner = true
                    )
                )
                workerSingleton.setWorker(
                    WorkerEntity(
                        id = worker,
                        name = castState.name,
                        surname = castState.surname,
                        password = castState.password,
                        phone = castState.phone,
                    )
                )
                preferenceHelper.setDataByKey(PreferenceKey.UserIsLoggedIn, true)
                setState(RegistrationUiState(isRegister = true))
            } catch (e: Exception) {
                AppLogger.log(e)
                handleError(e)
            }
        }
    }

    private fun submitEvent(event: RegistrationUiEvent.PhoneChanged) = safeLaunch {
        val castState =
            uiState.filterIsInstance<BaseViewState.Data<RegistrationUiState>>().map { it.value }
                .first()
        setState(
            castState.copy(
                phone = event.phone, inputDataErrorState = checkPhone(event.phone)
            )
        )
    }

    private fun checkPhone(phone: String): WorkerErrorState {
        if (phone.isBlank()) {
            return WorkerErrorState(phoneEmptyErrorState)
        }
        if (isValidUkrainianPhoneNumber(phone).not()) {
            return WorkerErrorState(
                phoneErrorState = FieldErrorState(
                    hasError = true, errorMessageStringResource = R.string.incorrect_phone_error
                )
            )
        }
        return WorkerErrorState()
    }


    private fun submitEvent(event: RegistrationUiEvent.PasswordChanged) = safeLaunch {
        val castState =
            uiState.filterIsInstance<BaseViewState.Data<RegistrationUiState>>().map { it.value }
                .first()
        setState(
            castState.copy(
                password = event.password, inputDataErrorState = checkPassword(event.password)
            )
        )
    }

    private fun checkPassword(password: String): WorkerErrorState {
        if (password.isBlank()) {
            return WorkerErrorState(passwordErrorState = passwordEmptyErrorState)
        }
        if (password.length <= 4) {
            return WorkerErrorState(passwordErrorState = passwordLengthErrorState)
        }
        return WorkerErrorState()
    }

    private fun submitEvent(event: RegistrationUiEvent.NameChanged) = safeLaunch {
        val castState =
            uiState.filterIsInstance<BaseViewState.Data<RegistrationUiState>>().map { it.value }
                .first()
        setState(
            castState.copy(
                name = event.name, inputDataErrorState = checkName(event.name)
            )
        )
    }

    private fun checkName(name: String): WorkerErrorState {
        if (name.isBlank()) {
            return WorkerErrorState(nameErrorState = emptyFieldErrorState)
        }
        return WorkerErrorState()
    }

    private fun submitEvent(event: RegistrationUiEvent.SurnameChanged) = safeLaunch {
        val castState =
            uiState.filterIsInstance<BaseViewState.Data<RegistrationUiState>>().map { it.value }
                .first()
        setState(
            castState.copy(
                surname = event.surname, inputDataErrorState = checkSurname(event.surname)
            )
        )
    }

    private fun checkSurname(surname: String): WorkerErrorState {
        if (surname.isBlank()) {
            return WorkerErrorState(surnameErrorState = emptyFieldErrorState)
        }
        return WorkerErrorState()
    }
}