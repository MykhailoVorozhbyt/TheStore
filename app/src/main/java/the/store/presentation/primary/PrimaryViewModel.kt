package the.store.presentation.primary

import com.example.core.base.vm.BaseStateViewModel
import com.example.core.data.repository.WorkerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import the.store.presentation.primary.models.PrimaryUiEvent
import the.store.presentation.primary.models.PrimaryUiState
import javax.inject.Inject

@HiltViewModel
class PrimaryViewModel @Inject constructor(
    private val workerRepository: WorkerRepository,
) : BaseStateViewModel<PrimaryUiState, PrimaryUiEvent>(PrimaryUiState()) {

    init {
        getWorker()
    }

    override fun onTriggerEvent(eventType: PrimaryUiEvent) {
        when (eventType) {
            is PrimaryUiEvent.SubmitXReportClick -> {
                println("SubmitXReportClick")
            }

            is PrimaryUiEvent.SubmitShiftReportClick -> {
                println("SubmitShiftReportClick")
            }

            is PrimaryUiEvent.SubmitCopyReportsClick -> {
                println("SubmitCopyReportsClick")
            }
        }
    }

    private fun getWorker() {
        safeLaunch {
            try {
                val worker = workerRepository.getWorkerById(workerSingleton.getWorker().id)
                println(worker)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}