package com.example.core.domain.entities

data class SaleHistoryEntity(
    val id: Long,
    val saleId: Long,
    val createdAt: Long,
    val fullPrice: Double,
    val products: Int,
)
