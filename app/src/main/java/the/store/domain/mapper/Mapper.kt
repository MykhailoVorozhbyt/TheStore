package the.store.domain.mapper

import com.example.core.domain.db_entity.CompanyDbEntity
import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.db_entity.SaleHistoryDbEntity
import com.example.core.domain.db_entity.WorkerDbEntity
import com.example.core.domain.entities.CompanyEntity
import com.example.core.domain.entities.SaleHistoryEntity
import com.example.core.domain.entities.WorkerEntity
import the.store.presentation.company.models.CompanyUiState
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

fun WorkerDbEntity.mapToWorkerEntity(): WorkerEntity =
    WorkerEntity(
        id = id,
        photo = photoUri.toUriOrNull(),
        name = name,
        surname = surname,
        phone = phone,
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

fun CompanyDbEntity.mapToCompanyUiState(): CompanyUiState = CompanyUiState(
    id = id,
    photoUri = photoUri.toUriOrNull(),
    companyName = name,
    description = description,
    companyCreated = companyCreated,
    createdAt = createdAt,
)

fun CompanyDbEntity.mapToCompanyEntity(): CompanyEntity = CompanyEntity(
    photoUri = photoUri,
    name = name,
    description = description,
    createdAt = createdAt,
)

fun CompanyUiState.mapToCompanyDbEntity(): CompanyDbEntity = CompanyDbEntity(
    id = id,
    photoUri = photoUri?.toString(),
    name = companyName,
    description = description,
    companyCreated = companyCreated,
    createdAt = createdAt,
)


fun SaleHistoryDbEntity.mapToSaleHistoryEntity(): SaleHistoryEntity = SaleHistoryEntity(
    id = id,
    saleId = saleId,
    createdAt = createdAt,
    fullPrice = fullPrice,
    products = products,
)