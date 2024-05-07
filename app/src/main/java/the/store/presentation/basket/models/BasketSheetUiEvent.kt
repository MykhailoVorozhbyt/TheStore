package the.store.presentation.basket.models

sealed class BasketSheetUiEvent : BaseUiEvent() {
    data object DeleteAllProductsClick : BasketSheetUiEvent()
    data class DeleteProductClick(val productId: Long) : BasketSheetUiEvent()
    data class PlusQuantityClick(val productId: Long) : BasketSheetUiEvent()
    data class MinusQuantityClick(val productId: Long) : BasketSheetUiEvent()
    data object SalleClick : BasketSheetUiEvent()
}