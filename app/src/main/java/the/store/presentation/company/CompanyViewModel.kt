package the.store.presentation.company

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import the.store.domain.mapper.mapToCompanyDbEntity
import the.store.domain.mapper.mapToCompanyUiState
import the.store.presentation.company.models.CompanyUiState
import the.store.presentation.company.models.OwnerUiEvent
import the.store.utils.validations.isCompanyNameValid
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val repository: CompanyRepository
) : MviViewModel<BaseViewState<CompanyUiState>, OwnerUiEvent>() {

    override fun onTriggerEvent(eventType: OwnerUiEvent) {
        when (eventType) {
            is OwnerUiEvent.CompanyNameChanged -> triggerEvent(eventType)
            is OwnerUiEvent.DeletePhotoUri -> triggerEvent(eventType)
            is OwnerUiEvent.InitUiContent -> getCompany()
            is OwnerUiEvent.PhotoChanged -> triggerEvent(eventType)
            is OwnerUiEvent.DescriptionChanged -> triggerEvent(eventType)
            is OwnerUiEvent.SubmitEditClick -> triggerEvent(eventType)
        }
    }

    private fun setStateData(state: CompanyUiState) {
        setState(BaseViewState.Data(state))
    }

    private suspend fun getState(): CompanyUiState {
        return uiState.filterIsInstance<BaseViewState.Data<CompanyUiState>>()
            .map { it.value }
            .first()
    }

    private fun getCompany() {
        successLaunch {
            val result = repository.getCompanyById(1L)
            setStateData(result?.mapToCompanyUiState() ?: CompanyUiState())
        }
    }

    private fun triggerEvent(event: OwnerUiEvent.CompanyNameChanged) {
        successLaunch {
            setStateData(
                getState().copy(
                    companyName = event.name,
                    inputErrorState = isCompanyNameValid(event.name)
                )
            )
        }
    }

    private fun triggerEvent(event: OwnerUiEvent.DeletePhotoUri) {
        successLaunch {
            setStateData(getState().copy(photoUri = null))
        }
    }

    private fun triggerEvent(event: OwnerUiEvent.PhotoChanged) {
        successLaunch {
            setStateData(
                getState().copy(photoUri = event.uri)
            )
        }
    }

    private fun triggerEvent(event: OwnerUiEvent.DescriptionChanged) {
        successLaunch {
            setStateData(getState().copy(description = event.description))
        }
    }

    private fun triggerEvent(event: OwnerUiEvent.SubmitEditClick) {
        successLaunch {
            val state = getState().copy(
                createdAt = Calendar.getInstance().timeInMillis
            )
            if (state.companyCreated) {
                repository.updateCompany(state.mapToCompanyDbEntity())
            } else {
                repository.insertCompany(state.mapToCompanyDbEntity().copy(companyCreated = true))
            }
            setStateData(state.copy(companyUpdate = true))
        }
    }

}