package com.example.core.domain.entities

data class CheckProductEntity(
    val id: Long,
    val photoUri: String?,
    val name: String,
    val price: Double,
    val fullPrice: Double,
    val quantity: Double,
    val measurementId: Int,
    val currencyId: Int,
)
