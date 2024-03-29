package com.example.core.data.repository

import com.example.core.data.dao.products.ProductsDao
import com.example.core.domain.db_entity.ProductDbEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val workerDao: ProductsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductsRepository {
    override suspend fun insertProduct(worker: ProductDbEntity): Long = withContext(ioDispatcher) {
        return@withContext workerDao.insertProduct(worker)
    }

    override suspend fun updateProduct(worker: ProductDbEntity) = withContext(ioDispatcher) {
        return@withContext workerDao.updateProduct(worker)
    }

    override suspend fun getProductById(id: Long): ProductDbEntity? = withContext(ioDispatcher) {
        return@withContext workerDao.getProductById(id)
    }

    override suspend fun deleteProductById(id: Long) = withContext(ioDispatcher) {
        return@withContext workerDao.deleteProductById(id)
    }

    override suspend fun getAllProducts(): List<ProductDbEntity> = withContext(ioDispatcher) {
        return@withContext workerDao.getAllProducts()
    }

    override suspend fun getProductsByName(name: String): List<ProductDbEntity> {
        if (name.isBlank()) {
            return workerDao.getAllProducts()
        }
        return workerDao.getProductsByName(name)
    }
}