package com.example.core.data.dao.sale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.core.domain.db_entity.check.CheckDbEntity
import com.example.core.domain.db_entity.check.CheckProductDbEntity

@Dao
interface SaleDao {

    @Insert
    suspend fun saleCheck(check: CheckDbEntity): Long

    @Query(GET_SALE_CHECK_BY_ID)
    suspend fun getSaleCheckById(id: Long): CheckDbEntity?

    @Update
    suspend fun updateSaleCheck(check: CheckDbEntity)

    @Query(GET_ALL_SOLD_CHECKS)
    suspend fun getAllChecks(): List<CheckDbEntity>?

    @Query(GET_ALL_SOLD_CHECKS_BY_VALUE)
    suspend fun getAllChecks(value: String): List<CheckDbEntity>?

    @Insert
    suspend fun insertCheckProduct(list: List<CheckProductDbEntity>)

    @Query(GET_CHECK_PRODUCT_BY_SALE_CHECK_ID)
    suspend fun getCheckProductBySaleCheckId(id: Long): List<CheckProductDbEntity>

}