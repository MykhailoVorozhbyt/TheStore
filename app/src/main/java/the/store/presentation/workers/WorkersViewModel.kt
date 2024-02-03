package the.store.presentation.workers

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.WorkerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import the.store.presentation.workers.models.WorkersUiEvent
import the.store.presentation.workers.models.WorkersUiState
import javax.inject.Inject

@HiltViewModel
class WorkersViewModel @Inject constructor(
    private val workerRepository: WorkerRepository
) : MviViewModel<BaseViewState<WorkersUiState>, WorkersUiEvent>() {

    override fun onTriggerEvent(eventType: WorkersUiEvent) {
        when (eventType) {
            is WorkersUiEvent.InputValueChanged -> triggerEvent(eventType)
            is WorkersUiEvent.InitUiScreen -> triggerEvent(eventType)
        }
    }

    private fun setStateData(state: WorkersUiState) {
        setState(BaseViewState.Data(state))
    }

    private suspend fun getState(): WorkersUiState {
        return uiState.filterIsInstance<BaseViewState.Data<WorkersUiState>>()
            .map { it.value }
            .first()
    }

    private fun triggerEvent(event: WorkersUiEvent.InputValueChanged) {
        getWorkersByName(event.inputValue)
    }

    private fun triggerEvent(event: WorkersUiEvent.InitUiScreen) {
        startLoading()
        getAllEmployers()
    }

    private fun getWorkersByName(name: String) {
        successLaunch {
            val result = workerRepository.getWorkersByName(name)
            setStateData(
                getState().copy(
                    isRefreshing = false,
                    searchedName = name,
                    workersList = result
                )
            )
        }
    }

    private fun getAllEmployers() {
        successLaunch {
            val result = workerRepository.getWorkersByName("")
            setStateData(WorkersUiState(searchedName = "", workersList = result))
        }
    }

}