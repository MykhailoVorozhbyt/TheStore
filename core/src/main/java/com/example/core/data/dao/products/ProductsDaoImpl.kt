package com.example.core.data.dao.products

import com.example.core.data.db.TheStoreDatabase
import com.example.core.domain.db_entity.ProductDbEntity
import javax.inject.Inject

class ProductsDaoImpl @Inject constructor(
    private val db: TheStoreDatabase
) : ProductsDao {
    override suspend fun insertWorker(worker: ProductDbEntity): Long =
        db.productsDao().insertWorker(worker)

    override suspend fun getAllProducts(): List<ProductDbEntity> =
        db.productsDao().getAllProducts()

    override suspend fun getAllProductsByName(name: String): List<ProductDbEntity> =
        db.productsDao().getAllProductsByName(name)
}