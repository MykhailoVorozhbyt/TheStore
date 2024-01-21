package com.example.core.data.repository

import com.example.core.domain.db_entity.ProductDbEntity

interface ProductsRepository {
    suspend fun insertWorker(worker: ProductDbEntity): Long
    suspend fun getAllProducts(): List<ProductDbEntity>
    suspend fun getAllProductsByName(name: String): List<ProductDbEntity>
}