package the.store.presentation.workers.create_edit_worker

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
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
            is CreateEditWorkerUiEvent.InitUI -> {}
        }
    }
}