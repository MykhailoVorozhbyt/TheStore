package com.example.core.domain.mapper

import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.db_entity.check.CheckProductDbEntity
import com.example.core.domain.entities.CheckProductEntity
import com.example.core.domain.entities.ProductEntity


fun ProductDbEntity.mapToProductEntity(): ProductEntity = ProductEntity(
    id = id,
    photoUri = photoUri,
    name = name,
    price = price,
    measurementId = measurementId,
    currencyId = currencyId,
)

fun ProductEntity.mapToCheckProductEntity(): CheckProductEntity {
    val quantity = 1.0
    val fullPrice = this.price * quantity
    return CheckProductEntity(
        id = id,
        photoUri = photoUri,
        name = name,
        price = price,
        fullPrice = fullPrice,
        quantity = quantity,
        measurementId = measurementId,
        currencyId = currencyId,
    )

}

fun CheckProductEntity.mapToCheckProductDbEntity(checkId: Long): CheckProductDbEntity =
    CheckProductDbEntity(
        checkId = checkId,
        photoUri = photoUri,
        name = name,
        price = price,
        fullPrice = fullPrice,
        quantity = quantity,
        measurementId = measurementId,
        currencyId = currencyId,
    )