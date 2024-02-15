package com.example.core.domain.entities

data class CompanyEntity(
    val photoUri: String? = null,
    val name: String,
    val description: String = "",
    val createdAt: Long,
)