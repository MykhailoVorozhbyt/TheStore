package the.store.presentation.primary

import com.example.core.base.vm.BaseStateViewModel
import com.example.core.data.repository.CompanyRepository
import com.example.core.data.repository.WorkerRepository
import com.example.core.domain.constants.Constants.COMPANY_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import the.store.domain.mapper.mapToCompanyEntity
import the.store.domain.mapper.mapToWorkerEntity
import the.store.presentation.primary.models.PrimaryUiEvent
import the.store.presentation.primary.models.PrimaryUiState
import javax.inject.Inject

@HiltViewModel
class PrimaryViewModel @Inject constructor(
    private val workerRepository: WorkerRepository,
    private val companyRepository: CompanyRepository
) : BaseStateViewModel<PrimaryUiState, PrimaryUiEvent>(PrimaryUiState()) {

    fun initData() {
        getWorker()
        getCompany()
    }

    override fun onTriggerEvent(eventType: PrimaryUiEvent) {
        when (eventType) {
            is PrimaryUiEvent.SubmitShiftReportClick -> {
                println("SubmitShiftReportClick")
            }
        }
    }

    private fun getWorker() {
        safeLaunch {
            try {
                setState(
                    uiState.value.copy(
                        workerInfo = workerSingleton.getWorker().mapToWorkerEntity()
                    )
                )
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun getCompany() {
        safeLaunch {
            try {
                val company = companyRepository.getCompanyById(COMPANY_ID)
                setState(uiState.value.copy(companyInfo = company?.mapToCompanyEntity()))
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}