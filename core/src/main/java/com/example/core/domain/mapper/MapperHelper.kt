package com.example.core.domain.mapper

import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.entities.ProductEntity


fun ProductDbEntity.mapToProductEntity(): ProductEntity =
    ProductEntity(
        id = id,
        photoUri = photoUri,
        name = name,
        price = price,
        measurementResId = measurementId,
        currencyResId = currencyId,
    )