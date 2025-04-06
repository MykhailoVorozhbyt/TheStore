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
        getSaleHistory("")
    }

    override fun onTriggerEvent(eventType: PrimaryUiEvent) {
        when (eventType) {
            is PrimaryUiEvent.Search -> getSaleHistory(eventType.value)
            is PrimaryUiEvent.ItemClick -> getCheckProductBySaleCheckId(eventType.id)
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

    private fun getSaleHistory(fiscalId: String) {
        safeLaunch {
            try {
                val result = saleRepository.getAllChecks(fiscalId)
                setState(uiState.value.copy(history = result?.map { it.mapToSaleHistoryEntity() }
                    ?: listOf()))
            } catch (e: Exception) {
                setState(uiState.value.copy(error = e.localizedMessage))
            }
        }
    }

    private fun getCheckProductBySaleCheckId(id: Long) = safeLaunch {
        if (uiState.value.history.find { it.id == id }?.products?.isEmpty() == true) {
            try {
                val result = saleRepository.getCheckProductBySaleCheckId(id)
                val newItem = uiState.value.history.first { it.id == id }.copy(products = result)
                val newHistory = uiState.value.history.map { if (it.id == id) newItem else it }
                setState(uiState.value.copy(history = newHistory))
            } catch (e: Exception) {
                setState(uiState.value.copy(error = e.localizedMessage))
            }
        }
    }

}