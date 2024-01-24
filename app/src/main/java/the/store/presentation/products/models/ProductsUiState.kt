package the.store.presentation.products.models

import androidx.compose.runtime.Stable
import com.example.core.domain.entities.ProductEntity

@Stable
data class ProductsUiState(
    val isRefreshing: Boolean = false,
    val searchedName: String = "",
    val productList: List<ProductEntity> = listOf()
)

val productList = listOf(
    ProductEntity(
        id = 1,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 1",
    ),
    ProductEntity(
        id = 2,
        photoUri = null,
        name = "Name 2",
    ),
    ProductEntity(
        id = 3,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 3",
    ),
    ProductEntity(
        id = 4,
        photoUri = null,
        name = "Name 4",
    ),
    ProductEntity(
        id = 5,
        photoUri = null,
        name = "Name 5",
    ),
    ProductEntity(
        id = 6,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 6",
    ),
    ProductEntity(
        id = 7,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 7",
    ),
    ProductEntity(
        id = 8,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 8",
    ),
    ProductEntity(
        id = 9,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 9",
    ),
    ProductEntity(
        id = 10,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 10",
    ),
    ProductEntity(
        id = 11,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 11",
    ),
    ProductEntity(
        id = 12,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 12",
    ),
    ProductEntity(
        id = 10,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 10",
    ),
    ProductEntity(
        id = 4,
        photoUri = null,
        name = "Name 4",
    ),
    ProductEntity(
        id = 5,
        photoUri = null,
        name = "Name 5",
    ),
    ProductEntity(
        id = 11,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 11",
    ),
    ProductEntity(
        id = 12,
        photoUri = "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=MPAE1nZhcJTKuMA7VnJdmzJqzhtcbxFHfv5CAjBH6jrA%3D%3D",
        name = "Name 12",
    ),


    )