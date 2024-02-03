package com.example.core.data.dao.products

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.core.domain.db_entity.ProductDbEntity

@Dao
interface ProductsDao {

    @Insert
    suspend fun insertProduct(worker: ProductDbEntity): Long

    @Update
    suspend fun updateProduct(worker: ProductDbEntity)

    @Query(GET_PRODUCT_BY_ID)
    suspend fun getProductById(id: Long): ProductDbEntity?

    @Query(DELETE_PRODUCT_BY_ID)
    suspend fun deleteProductById(id: Long)

    @Query(GET_ALL_PRODUCTS)
    suspend fun getAllProducts(): List<ProductDbEntity>

    @Query(GET_PRODUCTS_BY_NAME)
    suspend fun getProductsByName(name: String): List<ProductDbEntity>


}