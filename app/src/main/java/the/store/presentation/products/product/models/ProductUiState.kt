package the.store.presentation.products.product.models

import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
data class ProductUiState(
    val id: Long = 0,
    val photoUri: Uri? = null,
    val name: String = "",
    val price: Double = 0.0,
    val measurementId: Long = 0L,
    val currencyId: Long = 0L,
    val description: String = "",
    val barcode: String = "",
    val createdAt: Long = 0L,
    var inputDataErrorState: ProductErrorState = ProductErrorState(),
    val actionProduct: Boolean = false,
    val deleteProduct: Boolean = false
)
