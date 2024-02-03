package com.example.core.domain.mapper

import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.entities.ProductEntity

class DomainMapperImpl : DomainMapper {
    override fun mapToProductsEntity(list: List<ProductDbEntity>): List<ProductEntity> {
        return list.map { it.mapToProductEntity() }
    }
}