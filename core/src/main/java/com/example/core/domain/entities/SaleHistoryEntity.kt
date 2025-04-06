package com.example.core.domain.entities

import com.example.core.domain.db_entity.check.CheckProductDbEntity

data class SaleHistoryEntity(
    val id: Long,
    val saleId: Long,
    val createdAt: Long,
    val fullPrice: Double,
    val products: List<CheckProductDbEntity>,
)
