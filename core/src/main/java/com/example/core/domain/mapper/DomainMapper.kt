package com.example.core.domain.mapper

import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.entities.ProductEntity

interface DomainMapper {
    fun mapToProductsEntity(list: List<ProductDbEntity>): List<ProductEntity>
}
