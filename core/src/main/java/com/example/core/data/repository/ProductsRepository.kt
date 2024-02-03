package com.example.core.data.repository

import com.example.core.domain.db_entity.ProductDbEntity

interface ProductsRepository {
    suspend fun getAllProducts(): List<ProductDbEntity>
    suspend fun getProductsByName(name: String): List<ProductDbEntity>
    suspend fun insertProduct(worker: ProductDbEntity): Long
    suspend fun updateProduct(worker: ProductDbEntity)
    suspend fun getProductById(id: Long): ProductDbEntity?
    suspend fun deleteProductById(id: Long)
}