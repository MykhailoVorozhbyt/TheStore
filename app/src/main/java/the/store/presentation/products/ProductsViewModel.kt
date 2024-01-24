package the.store.presentation.products

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.ProductsRepository
import com.example.core.domain.mapper.DomainMapper
import dagger.hilt.android.lifecycle.HiltViewModel
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
            is ProductsUiEvent.RefreshList -> triggerEvent(eventType)
        }
    }

    private fun setStateData(state: ProductsUiState) {
        setState(BaseViewState.Data(state))
    }


    private fun triggerEvent(event: ProductsUiEvent.InitUiScreen) {
        startLoading()
        successLaunch {
            val workers = repository.getAllProducts()
            setStateData(ProductsUiState(productList = mapper.mapToProductsEntity(workers)))
//            setStateData(ProductsUiState(productList = productList))
        }
    }

    private fun triggerEvent(event: ProductsUiEvent.InputValueChanged) {
        startLoading()
        successLaunch {
            val workers = repository.getAllProductsByName(event.inputValue)
            setStateData(ProductsUiState(productList = mapper.mapToProductsEntity(workers)))
        }
    }

    private fun triggerEvent(event: ProductsUiEvent.RefreshList) {
        successLaunch {
            val workers = repository.getAllProducts()
            setStateData(ProductsUiState(productList = mapper.mapToProductsEntity(workers)))
        }
    }
}