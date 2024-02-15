package com.example.core.data.dao.sale

import androidx.room.Dao
import androidx.room.Query
import com.example.core.domain.db_entity.SaleHistoryDbEntity

@Dao
interface SaleDao {

    @Query(GET_ALL_SALE_HISTORY)
    suspend fun getSaleHistory(): List<SaleHistoryDbEntity>

    @Query(GET_SALE_HISTORY)
    suspend fun getSaleHistory(value: String): List<SaleHistoryDbEntity>
}