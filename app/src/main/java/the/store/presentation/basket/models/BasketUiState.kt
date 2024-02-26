package the.store.presentation.basket.models

import com.example.core.domain.entities.CheckProductEntity
import com.example.core.domain.entities.ProductEntity
import com.example.theme.R
import the.store.presentation.products.models.ProductEntityList

data class BasketUiState(
    val searchedName: String = "",
    val productList: List<ProductEntity> = ProductEntityList(),
    val basketProducts: List<CheckProductEntity> = CheckProductEntityList(),
)

fun CheckProductEntityList(): List<CheckProductEntity> {
    val list: MutableList<CheckProductEntity> = mutableListOf()
    repeat(10) {
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