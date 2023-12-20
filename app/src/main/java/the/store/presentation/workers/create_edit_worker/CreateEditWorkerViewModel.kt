package the.store.presentation.workers.create_edit_worker

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiEvent
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiState
import javax.inject.Inject

@HiltViewModel
class CreateEditWorkerViewModel @Inject constructor(
    private val workerRepository: WorkerRepository,
    private val dispatchers: AppDispatchers,
) :
    MviViewModel<BaseViewState<CreateEditWorkerUiState>, CreateEditWorkerUiEvent>() {
    override fun onTriggerEvent(eventType: CreateEditWorkerUiEvent) {
        when (eventType) {
            is CreateEditWorkerUiEvent.NameChanged -> {}
            is CreateEditWorkerUiEvent.PasswordChanged -> {}
            is CreateEditWorkerUiEvent.PhoneChanged -> {}
            is CreateEditWorkerUiEvent.SubmitCreateEditClick -> {}
            is CreateEditWorkerUiEvent.SurnameChanged -> {}
            is CreateEditWorkerUiEvent.EmailAddressChanged -> {}
            is CreateEditWorkerUiEvent.InitUiContent -> triggerEvent(eventType)
        }
    }

    private fun setDataState(state: CreateEditWorkerUiState) {
        setState(BaseViewState.Data(state))
    }

    private fun triggerEvent(eventType: CreateEditWorkerUiEvent.InitUiContent) {
        setDataState(CreateEditWorkerUiState())
    }


    fun createOrUpdateWorker() {
        safeLaunch(dispatchers.io) {
            try {
                val castState =
                    uiState.filterIsInstance<BaseViewState.Data<CreateEditWorkerUiState>>()
                        .map { it.value }.first()
                if (castState.id == 0L) {
                    workerRepository.insertWorker(castState.mapToWorkerEntity())
                    return@safeLaunch
                }
                workerRepository.updateWorker(castState.mapToWorkerEntity())
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }


}