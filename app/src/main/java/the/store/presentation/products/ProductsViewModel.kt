package the.store.presentation.products

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.ProductsRepository
import com.example.core.domain.mapper.DomainMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import the.store.presentation.products.models.ProductsUiEvent
import the.store.presentation.products.models.ProductsUiState
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ProductsRepository,
    private val mapper: DomainMapper
) : MviViewModel<BaseViewState<ProductsUiState>, ProductsUiEvent>() {
    override fun onTriggerEvent(eventType: ProductsUiEvent) {
        when (eventType) {
            is ProductsUiEvent.InitUiScreen -> triggerEvent(eventType)
            is ProductsUiEvent.InputValueChanged -> triggerEvent(eventType)
        }
    }

    private fun setStateData(state: ProductsUiState) {
        setState(BaseViewState.Data(state))
    }


    private fun triggerEvent(event: ProductsUiEvent.InitUiScreen) {
        startLoading()
        getAllProducts()
    }

    private fun getAllProducts() {
        successLaunch {
            val workers = repository.getAllProducts()
            setStateData(ProductsUiState("", mapper.mapToProductsEntity(workers)))
        }
    }

    private fun triggerEvent(event: ProductsUiEvent.InputValueChanged) {
        successLaunch {
            val workers = repository.getProductsByName(event.inputValue)
            setStateData(
                getState().copy(
                    searchedName = event.inputValue,
                    productList = mapper.mapToProductsEntity(workers)
                )
            )
        }
    }


    private suspend fun getState(): ProductsUiState {
        return uiState.filterIsInstance<BaseViewState.Data<ProductsUiState>>()
            .map { it.value }
            .first()
    }

}