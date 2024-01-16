package the.store.presentation.workers.create_edit_worker

import android.content.Context
import android.net.Uri
import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppDispatchers
import com.example.core.utils.BitmapConverters
import com.example.theme.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import the.store.presentation.login_to_app.registration.models.WorkerErrorState
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiEvent
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiState
import the.store.utils.isNameValid
import the.store.utils.isPasswordValid
import the.store.utils.isPhoneValid
import the.store.utils.isSurnameValid
import the.store.utils.isValidEmail
import the.store.utils.workerValidateInputs
import javax.inject.Inject

@HiltViewModel
class CreateEditWorkerViewModel @Inject constructor(
    private val workerRepository: WorkerRepository,
    private val dispatchers: AppDispatchers,
    @ApplicationContext private val appContext: Context
) :
    MviViewModel<BaseViewState<CreateEditWorkerUiState>, CreateEditWorkerUiEvent>() {
    override fun onTriggerEvent(eventType: CreateEditWorkerUiEvent) {
        when (eventType) {
            is CreateEditWorkerUiEvent.PhotoChanged -> triggerEvent(eventType)
            is CreateEditWorkerUiEvent.NameChanged -> triggerEvent(eventType)
            is CreateEditWorkerUiEvent.PasswordChanged -> triggerEvent(eventType)
            is CreateEditWorkerUiEvent.PhoneChanged -> triggerEvent(eventType)
            is CreateEditWorkerUiEvent.SubmitCreateEditClick -> triggerEvent(eventType)
            is CreateEditWorkerUiEvent.SurnameChanged -> triggerEvent(eventType)
            is CreateEditWorkerUiEvent.EmailAddressChanged -> triggerEvent(eventType)
            is CreateEditWorkerUiEvent.InitUiContent -> triggerEvent(eventType)
        }
    }

    private suspend fun getState(): CreateEditWorkerUiState {
        return uiState.filterIsInstance<BaseViewState.Data<CreateEditWorkerUiState>>()
            .map { it.value }
            .first()
    }

    private fun setNewDataState(state: CreateEditWorkerUiState) {
        setState(BaseViewState.Data(state))
    }

    private fun triggerEvent(event: CreateEditWorkerUiEvent.PhotoChanged) {
        safeLaunch {
            setNewDataState(
                getState().copy(
                    photoUri = event.uri
                )
            )
        }
    }

    private fun triggerEvent(event: CreateEditWorkerUiEvent.NameChanged) {
        safeLaunch {
            setNewDataState(
                getState().copy(
                    name = event.name, inputDataErrorState = isNameValid(event.name)
                )
            )
        }
    }

    private fun triggerEvent(event: CreateEditWorkerUiEvent.PasswordChanged) {
        safeLaunch {
            setNewDataState(
                getState().copy(
                    password = event.password, inputDataErrorState = isPasswordValid(event.password)
                )
            )
        }
    }

    private fun triggerEvent(event: CreateEditWorkerUiEvent.PhoneChanged) {
        safeLaunch {
            setNewDataState(
                getState().copy(
                    phone = event.phone, inputDataErrorState = isPhoneValid(event.phone)
                )
            )
        }
    }

    private fun triggerEvent(event: CreateEditWorkerUiEvent.SurnameChanged) {
        safeLaunch {
            setNewDataState(
                getState().copy(
                    surname = event.surname, inputDataErrorState = isSurnameValid(event.surname)
                )
            )
        }
    }

    private fun triggerEvent(event: CreateEditWorkerUiEvent.EmailAddressChanged) {
        safeLaunch {
            setNewDataState(
                getState().copy(
                    emailAddress = event.email, inputDataErrorState = isValidEmail(event.email)
                )
            )
        }
    }

    private fun triggerEvent(eventType: CreateEditWorkerUiEvent.InitUiContent) {
        setNewDataState(CreateEditWorkerUiState())
    }

    private fun triggerEvent(event: CreateEditWorkerUiEvent.SubmitCreateEditClick) = safeLaunch {
        val state = getState()
        val validation = workerValidateInputs(
            state.name,
            state.surname,
            state.phone,
            state.password,
            state.emailAddress,
        )
        if (validation != WorkerErrorState()) {
            setNewDataState(getState().copy(inputDataErrorState = validation))
            return@safeLaunch
        }
        createOrUpdateWorker()
    }

    private fun createOrUpdateWorker() {
        safeLaunch(dispatchers.io) {
            try {
                val castState =
                    uiState.filterIsInstance<BaseViewState.Data<CreateEditWorkerUiState>>()
                        .map { it.value }.first()
                val newModel = castState.mapToWorkerEntity(castState.photoUri.toString())

                if (castState.id == 0L) {
                    workerRepository.insertWorker(newModel)
                    setNewDataState(CreateEditWorkerUiState(userDoneNotification = R.string.employee_created_successfully))
                    return@safeLaunch
                }
                workerRepository.updateWorker(newModel)
                setNewDataState(CreateEditWorkerUiState(userDoneNotification = R.string.employee_data_updated_successfully))
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }


}