package the.store.presentation.products.product

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.ProductsRepository
import com.example.core.utils.elvis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import the.store.domain.mapper.mapToProductDbEntity
import the.store.domain.mapper.mapToProductUiState
import the.store.presentation.products.product.models.ProductErrorState
import the.store.presentation.products.product.models.ProductUiEvent
import the.store.presentation.products.product.models.ProductUiState
import the.store.utils.format
import the.store.utils.validations.isProductNameValid
import the.store.utils.validations.isProductPriceValid
import the.store.utils.validations.isProductValidateInputs
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductsRepository
) : MviViewModel<BaseViewState<ProductUiState>, ProductUiEvent>() {
    override fun onTriggerEvent(eventType: ProductUiEvent) {
        when (eventType) {
            is ProductUiEvent.DeletePhotoUri -> triggerEvent(eventType)
            is ProductUiEvent.InitUiContent -> triggerEvent(eventType)
            is ProductUiEvent.MeasurementsChanged -> triggerEvent(eventType)
            is ProductUiEvent.CurrencyChanged -> triggerEvent(eventType)
            is ProductUiEvent.NameChanged -> triggerEvent(eventType)
            is ProductUiEvent.PhotoChanged -> triggerEvent(eventType)
            is ProductUiEvent.PriceChanged -> triggerEvent(eventType)
            is ProductUiEvent.DescriptionChanged -> triggerEvent(eventType)
            is ProductUiEvent.BarcodeChanged -> triggerEvent(eventType)
            is ProductUiEvent.SubmitCreateEditClick -> triggerEvent(eventType)
        }
    }

    private suspend fun getState(): ProductUiState {
        return uiState.filterIsInstance<BaseViewState.Data<ProductUiState>>()
            .map { it.value }
            .first()
    }

    private fun setNewDataState(state: ProductUiState) {
        setState(BaseViewState.Data(state))
    }

    private fun triggerEvent(eventType: ProductUiEvent.InitUiContent) {
        if (eventType.productId == 0L) {
            setNewDataState(ProductUiState())
        } else {
            getProductById(eventType.productId)
        }
    }

    private fun triggerEvent(eventType: ProductUiEvent.PhotoChanged) {
        successLaunch {
            setNewDataState(
                getState().copy(photoUri = eventType.uri)
            )
        }
    }

    private fun triggerEvent(eventType: ProductUiEvent.DeletePhotoUri) {
        successLaunch {
            setNewDataState(
                getState().copy(
                    photoUri = null
                )
            )
        }
    }

    private fun triggerEvent(eventType: ProductUiEvent.NameChanged) {
        successLaunch {
            setNewDataState(
                getState().copy(
                    name = eventType.name,
                    inputDataErrorState = isProductNameValid(eventType.name)
                )
            )
        }
    }

    private fun triggerEvent(eventType: ProductUiEvent.PriceChanged) {
        successLaunch {
            val price = eventType.price.toDoubleOrNull().elvis().format()
            setNewDataState(
                getState().copy(
                    price = price,
                    inputDataErrorState = isProductPriceValid(price)
                )
            )
        }
    }

    private fun triggerEvent(eventType: ProductUiEvent.MeasurementsChanged) {
        successLaunch {
            setNewDataState(
                getState().copy(
                    measurementId = eventType.measurementsId
                )
            )
        }
    }

    private fun triggerEvent(eventType: ProductUiEvent.CurrencyChanged) {
        successLaunch {
            setNewDataState(
                getState().copy(
                    measurementId = eventType.currencyId
                )
            )
        }
    }

    private fun triggerEvent(eventType: ProductUiEvent.DescriptionChanged) {
        successLaunch {
            setNewDataState(
                getState().copy(
                    description = eventType.description,
                )
            )
        }
    }

    private fun triggerEvent(eventType: ProductUiEvent.BarcodeChanged) {
        successLaunch {
            setNewDataState(
                getState().copy(
                    barcode = eventType.barcode,
                )
            )
        }
    }

    private fun triggerEvent(eventType: ProductUiEvent.SubmitCreateEditClick) {
        successLaunch {
            val state = getState()
            val validation = isProductValidateInputs(
                state.name,
                state.price
            )
            if (validation != ProductErrorState()) {
                setNewDataState(getState().copy(inputDataErrorState = validation))
                return@successLaunch
            }
            createOrUpdateProduct()
        }
    }

    private fun createOrUpdateProduct() {
        successLaunch {
            try {
                val newModel = getState().mapToProductDbEntity()
                    .copy(createdAt = Calendar.getInstance().timeInMillis)
                if (getState().id == 0L) {
                    repository.insertProduct(newModel)
                    setNewDataState(getState().copy(userDoneNotification = com.example.theme.R.string.product_created_successfully))
                    return@successLaunch
                }
                repository.insertProduct(newModel)
                setNewDataState(getState().copy(userDoneNotification = com.example.theme.R.string.product_data_updated_successfully))
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun getProductById(id: Long) {
        successLaunch {
            val result = repository.getProductById(id)
            if (result == null) {
                handleError(Exception("Product Error"))
                return@successLaunch
            }
            setNewDataState(result.mapToProductUiState())
        }
    }
}