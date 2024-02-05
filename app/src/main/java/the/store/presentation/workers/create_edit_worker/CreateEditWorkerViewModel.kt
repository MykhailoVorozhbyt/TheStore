package the.store.presentation.workers.create_edit_worker

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import the.store.domain.mapper.mapToCreateEditWorkerUiState
import the.store.domain.mapper.mapToWorkerEntity
import the.store.presentation.login_to_app.registration.models.WorkerErrorState
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiEvent
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiState
import the.store.utils.validations.isNameValid
import the.store.utils.validations.isPasswordValid
import the.store.utils.validations.isPhoneValid
import the.store.utils.validations.isSurnameValid
import the.store.utils.validations.isValidEmail
import the.store.utils.validations.workerValidateInputs
import javax.inject.Inject

@HiltViewModel
class CreateEditWorkerViewModel @Inject constructor(
    private val repository: WorkerRepository,
    private val dispatchers: AppDispatchers
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
            is CreateEditWorkerUiEvent.DeletePhotoUri -> triggerEvent(eventType)
            is CreateEditWorkerUiEvent.DeleteEmployerClick -> triggerEvent(eventType)
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

    private fun triggerEvent(event: CreateEditWorkerUiEvent.DeletePhotoUri) {
        safeLaunch {
            setNewDataState(
                getState().copy(
                    photoUri = null
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
        if (eventType.workerId == 0L) {
            setNewDataState(CreateEditWorkerUiState())
        } else {
            getWorkerById(eventType.workerId)
        }
    }

    private fun triggerEvent(eventType: CreateEditWorkerUiEvent.DeleteEmployerClick) {
        deleteEmployer(eventType.workerId)
    }

    private fun deleteEmployer(id: Long) {
        successLaunch {
            repository.deleteEmployerById(id)
            setNewDataState(getState().copy(deleteProduct = true))
        }
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
                val newModel = getState().mapToWorkerEntity()
                if (newModel.id == 0L) {
                    repository.insertWorker(newModel)
                    setNewDataState(getState().copy(actionProduct = true))
                    return@safeLaunch
                }
                repository.updateWorker(newModel)
                setNewDataState(getState().copy(actionProduct = true))
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun getWorkerById(id: Long) {
        safeLaunch {
            try {
                val worker = repository.getWorkerById(id)
                if (worker == null) {
                    handleError(Exception("Worker Error"))
                    return@safeLaunch
                }
                setNewDataState(worker.mapToCreateEditWorkerUiState())
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }


}