package the.store.presentation.primary

import com.example.core.base.vm.BaseStateViewModel
import com.example.core.data.repository.CompanyRepository
import com.example.core.data.repository.SaleRepository
import com.example.core.domain.constants.Constants.COMPANY_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import the.store.domain.mapper.mapToCompanyEntity
import the.store.domain.mapper.mapToSaleHistoryEntity
import the.store.domain.mapper.mapToWorkerEntity
import the.store.presentation.primary.models.PrimaryUiEvent
import the.store.presentation.primary.models.PrimaryUiState
import javax.inject.Inject

@HiltViewModel
class PrimaryViewModel @Inject constructor(
    private val companyRepository: CompanyRepository,
    private val saleRepository: SaleRepository,
) : BaseStateViewModel<PrimaryUiState, PrimaryUiEvent>(PrimaryUiState()) {

    fun initData() {
        getWorker()
        getCompany()
    }

    override fun onTriggerEvent(eventType: PrimaryUiEvent) {
        when (eventType) {
            is PrimaryUiEvent.Search -> getSaleHistory(eventType.value)
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
                setState(uiState.value.copy(error = e.localizedMessage))
            }
        }
    }

    private fun getCompany() {
        safeLaunch {
            try {
                val company = companyRepository.getCompanyById(COMPANY_ID)
                setState(uiState.value.copy(companyInfo = company?.mapToCompanyEntity()))
            } catch (e: Exception) {
                setState(uiState.value.copy(error = e.localizedMessage))
            }
        }
    }

    private fun getSaleHistory(value: String) {
        safeLaunch {
            try {
                val result = saleRepository.getSaleHistory(value)
                setState(uiState.value.copy(history = result.map { it.mapToSaleHistoryEntity() }))
            } catch (e: Exception) {
                setState(uiState.value.copy(error = e.localizedMessage))
            }
        }
    }

}