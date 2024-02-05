package the.store.presentation.products.product.models

import android.net.Uri

sealed class ProductUiEvent {
    data class InitUiContent(val productId: Long) : ProductUiEvent()
    data class PhotoChanged(val uri: Uri) : ProductUiEvent()
    data object DeletePhotoUri : ProductUiEvent()
    data class NameChanged(val name: String) : ProductUiEvent()
    data class PriceChanged(val price: String) : ProductUiEvent()
    data class MeasurementsChanged(val measurementsId: Long) : ProductUiEvent()
    data class CurrencyChanged(val currencyId: Long) : ProductUiEvent()
    data class DescriptionChanged(val description: String) : ProductUiEvent()
    data class BarcodeChanged(val barcode: String) : ProductUiEvent()

    data class  DeleteProductClick(val productId: Long) : ProductUiEvent()
    data object SubmitCreateEditClick : ProductUiEvent()
}