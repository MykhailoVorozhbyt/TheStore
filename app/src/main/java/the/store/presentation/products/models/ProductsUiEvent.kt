package the.store.presentation.products.models

sealed class ProductsUiEvent {
    data class InputValueChanged(val inputValue: String) : ProductsUiEvent()
    object InitUiScreen : ProductsUiEvent()
}