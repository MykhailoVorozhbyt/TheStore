package the.store.presentation.workers

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.WorkerRepository
import com.example.core.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import the.store.presentation.workers.models.WorkersUiEvent
import the.store.presentation.workers.models.WorkersUiState
import javax.inject.Inject

@HiltViewModel
class WorkersViewModel @Inject constructor(
    private val workerRepository: WorkerRepository,
    private val dispatchers: AppDispatchers,
) : MviViewModel<BaseViewState<WorkersUiState>, WorkersUiEvent>() {

    override fun onTriggerEvent(eventType: WorkersUiEvent) {
        when (eventType) {
            is WorkersUiEvent.InputValueChanged -> {
                setStateData(WorkersUiState(isRefreshing = true))
                getWorkersByName(eventType.inputValue)
            }

            is WorkersUiEvent.InitUiScreen -> {
                startLoading()
                getWorkersByName("")
            }

            is WorkersUiEvent.RefreshList -> {
                setStateData(WorkersUiState(isRefreshing = true))
                getWorkersByName("")
            }
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

    private fun getWorkersByName(name: String) {
        safeLaunch(dispatchers.io) {
            try {
                val result = workerRepository.getWorkersByName(name)
                delay(1000L)
                setStateData(
                    WorkersUiState(
                        isRefreshing = false,
                        searchedName = name,
                        workersList = result
                    )
                )
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

}