package the.store.domain.mapper

import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.db_entity.WorkerDbEntity
import the.store.presentation.products.product.models.ProductUiState
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiState
import the.store.utils.toUriOrNull

fun WorkerDbEntity.mapToCreateEditWorkerUiState(): CreateEditWorkerUiState =
    CreateEditWorkerUiState(
        id = id,
        photoUri = photoUri.toUriOrNull(),
        name = name,
        surname = surname,
        phone = phone,
        password = password,
        emailAddress = emailAddress,
    )

fun CreateEditWorkerUiState.mapToWorkerEntity(): WorkerDbEntity {
    return WorkerDbEntity(
        id = id,
        name = name,
        photoUri = photoUri?.toString(),
        surname = surname,
        password = password,
        phone = phone,
        emailAddress = emailAddress
    )
}

fun ProductUiState.mapToProductDbEntity(): ProductDbEntity {
    return ProductDbEntity(
        id = id,
        photoUri = photoUri?.toString(),
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
    photoUri = photoUri.toUriOrNull(),
    name = name,
    price = price,
    measurementId = measurementId,
    description = description,
    barcode = barcode,
    createdAt = createdAt,
)