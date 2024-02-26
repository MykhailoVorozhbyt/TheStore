package the.store.presentation.basket

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import the.store.presentation.basket.models.BaseUiEvent
import the.store.presentation.basket.models.BasketSheetUiEvent
import the.store.presentation.basket.models.BasketUiEvent
import the.store.presentation.basket.models.BasketUiState
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor() :
    MviViewModel<BaseViewState<BasketUiState>, BaseUiEvent>() {

    override fun onTriggerEvent(eventType: BaseUiEvent) {
        when (eventType) {
            is BasketSheetUiEvent -> onTriggerEvent(eventType)
            is BasketUiEvent -> onTriggerEvent(eventType)
        }
    }

    private fun onTriggerEvent(event: BasketSheetUiEvent) {
        when (event) {
            is BasketSheetUiEvent.SalleClick -> triggerEvent(event)
            is BasketSheetUiEvent.DeleteAllProductsClick -> triggerEvent(event)
            is BasketSheetUiEvent.DeleteProductClick -> triggerEvent(event)
        }
    }

    private fun onTriggerEvent(event: BasketUiEvent) {
        when (event) {
            is BasketUiEvent.InitUi -> triggerEvent(event)
            is BasketUiEvent.SearchProduct -> triggerEvent(event)
            is BasketUiEvent.ProductClick -> triggerEvent(event)
        }
    }

    private fun triggerEvent(event: BasketUiEvent.InitUi) {
        startLoading()
        setNewDataState(
            BasketUiState()
        )
    }

    private fun triggerEvent(event: BasketUiEvent.SearchProduct) {
        print(event.toString())
    }

    private fun triggerEvent(event: BasketUiEvent.ProductClick) {
        print(event.toString())
    }

    private fun triggerEvent(event: BasketSheetUiEvent.SalleClick) {
        print(event.toString())
    }

    private fun triggerEvent(event: BasketSheetUiEvent.DeleteAllProductsClick) {
        print(event.toString())
    }

    private fun triggerEvent(event: BasketSheetUiEvent.DeleteProductClick) {
        print(event.toString())
    }

    private fun setNewDataState(state: BasketUiState) {
        setState(BaseViewState.Data(state))
    }


}