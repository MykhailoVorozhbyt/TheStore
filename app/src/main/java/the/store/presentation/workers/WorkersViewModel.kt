package the.store.presentation.workers

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import the.store.presentation.workers.models.WorkersUiEvent
import the.store.presentation.workers.models.WorkersUiState
import the.store.presentation.workers.models.workersList
import javax.inject.Inject

@HiltViewModel
class WorkersViewModel @Inject constructor(
    private val workerRepository: WorkerRepository,
    private val dispatchers: AppDispatchers,
) : MviViewModel<BaseViewState<WorkersUiState>, WorkersUiEvent>() {

    override fun onTriggerEvent(eventType: WorkersUiEvent) {
        when (eventType) {
            is WorkersUiEvent.InputValueChanged -> {
                if (eventType.inputValue.isBlank()) return
                getWorkersByName(eventType.inputValue)
            }

            WorkersUiEvent.InitUiScreen -> getWorkersByName("")
        }
    }

    private fun setState(state: WorkersUiState) {
        setState(BaseViewState.Data(state))
    }

    private fun getWorkersByName(name: String) {
        startLoading()
        safeLaunch(dispatchers.io) {
            try {
                val result = workerRepository.getWorkersByName(name)
                setState(
                    WorkersUiState(
                        searchedName = name,
                        workersList = workersList
                    )
                )
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

}