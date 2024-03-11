package the.store.presentation.basket.models

import com.example.core.domain.entities.ProductEntity

sealed class BasketUiEvent : BaseUiEvent() {
    data object InitUi : BasketUiEvent()
    data class ProductClick(val product: ProductEntity) : BasketUiEvent()
    data class SearchProduct(val value: String) : BasketUiEvent()
}