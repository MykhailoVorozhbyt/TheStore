package com.example.core.data.repository

import com.example.core.domain.db_entity.SaleHistoryDbEntity

interface SaleRepository {
    /**
     * Sale History
     * */
    suspend fun getSaleHistory(): List<SaleHistoryDbEntity>

    suspend fun getSaleHistory(value: String): List<SaleHistoryDbEntity>
}