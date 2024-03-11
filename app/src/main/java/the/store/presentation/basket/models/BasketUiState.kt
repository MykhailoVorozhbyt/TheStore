package the.store.presentation.basket.models

import androidx.compose.runtime.Immutable
import com.example.core.domain.entities.CheckProductEntity
import com.example.core.domain.entities.ProductEntity
import com.example.theme.R

@Immutable
data class BasketUiState(
    val searchedProduct: String = "",
    val productList: List<ProductEntity> = listOf(),
    val basketFullPrice: Double = 0.0,
    val basketProducts: List<CheckProductEntity> = listOf(),
    val isCheckSold: Boolean = false
)

fun CheckProductEntityList(): List<CheckProductEntity> {
    val list: MutableList<CheckProductEntity> = mutableListOf()
    repeat(3) {
        list.add(
            CheckProductEntity(
                it.toLong(),
                photoUri = null,
                "Product: $it",
                1100.0,
                1100.0 * 2.0,
                2.0,
                R.string.kilogram,
                R.string.usd
            )
        )
    }
    return list
}