package com.example.core.domain.entities

data class ProductEntity(
    val id: Long,
    val photoUri: String?,
    val name: String,
    val price: Double,
    val measurementResId: Int,
    val currencyResId: Int,
)