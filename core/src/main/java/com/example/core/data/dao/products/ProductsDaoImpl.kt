package com.example.core.data.dao.products

import com.example.core.data.db.TheStoreDatabase
import com.example.core.domain.db_entity.ProductDbEntity
import javax.inject.Inject

class ProductsDaoImpl @Inject constructor(
    private val db: TheStoreDatabase
) : ProductsDao {
    override suspend fun insertProduct(worker: ProductDbEntity): Long =
        db.productsDao().insertProduct(worker)

    override suspend fun getProductById(id: Long): ProductDbEntity? =
        db.productsDao().getProductById(id)

    override suspend fun getAllProducts(): List<ProductDbEntity> =
        db.productsDao().getAllProducts()

    override suspend fun getAllProductsByName(name: String): List<ProductDbEntity> =
        db.productsDao().getAllProductsByName(name)
}