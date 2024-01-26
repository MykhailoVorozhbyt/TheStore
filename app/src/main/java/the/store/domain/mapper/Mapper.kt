package the.store.domain.mapper

import android.net.Uri
import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.db_entity.WorkerDbEntity
import the.store.presentation.products.product.models.ProductUiState
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiState

fun WorkerDbEntity.mapToCreateEditWorkerUiState(): CreateEditWorkerUiState =
    CreateEditWorkerUiState(
        id = id,
        photoUri = Uri.parse(photoUri),
        name = name,
        surname = surname,
        phone = phone,
        password = password,
        emailAddress = emailAddress,
    )

fun ProductUiState.mapToProductDbEntity(): ProductDbEntity {
    val photoUri = if (this.photoUri == null) null else this.photoUri.toString()
    return ProductDbEntity(
        photoUri = photoUri,
        name = name,
        price = price,
        measurementId = measurementId,
        description = description,
        barcode = barcode,
        createdAt = createdAt,
    )
}

fun ProductDbEntity.mapToProductUiState(): ProductUiState = ProductUiState(
    id = id,
    photoUri = Uri.parse(photoUri),
    name = name,
    price = price,
    measurementId = measurementId,
    description = description,
    barcode = barcode,
    createdAt = createdAt,
)