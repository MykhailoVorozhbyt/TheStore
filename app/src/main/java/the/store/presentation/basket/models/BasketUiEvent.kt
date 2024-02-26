package the.store.presentation.basket.models

sealed class BasketUiEvent : BaseUiEvent() {
    data object InitUi : BasketUiEvent()
    data class ProductClick(val id: Long) : BasketUiEvent()
    data class SearchProduct(val string: String) : BasketUiEvent()
}