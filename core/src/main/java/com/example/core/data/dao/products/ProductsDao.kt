package com.example.core.data.dao.products

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.db_entity.ProductDbEntity

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorker(worker: ProductDbEntity): Long

    @Query(GET_ALL_PRODUCTS)
    suspend fun getAllProducts(): List<ProductDbEntity>

    @Query(GET_PRODUCTS_BY_NAME)
    suspend fun getAllProductsByName(name: String): List<ProductDbEntity>
}