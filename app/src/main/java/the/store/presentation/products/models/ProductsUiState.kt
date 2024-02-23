package the.store.presentation.products.models

import androidx.compose.runtime.Immutable
import com.example.core.domain.entities.ProductEntity
import com.example.theme.R
import kotlin.random.Random

@Immutable
data class ProductsUiState(
    val searchedName: String = "",
    val productList: List<ProductEntity> = listOf()
)

fun productList(): List<ProductEntity> {
    val list: MutableList<ProductEntity> = mutableListOf()
    repeat(5) {
        list.add(
            if (Random.nextBoolean()) {
                ProductEntity(
                    it.toLong(),
                    photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
                    "Product: $it",
                    1500.0, R.string.kilogram,
                    R.string.usd
                )
            } else {
                ProductEntity(
                    it.toLong(),
                    photoUri = null,
                    "Product: $it",
                    1100.0, R.string.kilogram,
                    R.string.usd
                )
            }
        )
    }
    return list
}