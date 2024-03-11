package com.example.core.data.repository

import com.example.core.domain.db_entity.SaleHistoryDbEntity
import com.example.core.domain.db_entity.check.CheckDbEntity
import com.example.core.domain.db_entity.check.CheckProductDbEntity

interface SaleRepository {
    /**
     * Sale
     * */

    suspend fun saleCheck(check: CheckDbEntity): Long

    suspend fun updateSaleCheck(check: CheckDbEntity)

    suspend fun getSaleCheckById(id: Long): CheckDbEntity?


    suspend fun insertCheckProduct(list: List<CheckProductDbEntity>)

    suspend fun getCheckProductBySaleCheckId(saleCheckId: Long): List<CheckProductDbEntity>

    /**
     * Sale History
     * */
    suspend fun getSaleHistory(): List<SaleHistoryDbEntity>

    suspend fun getSaleHistory(value: String): List<SaleHistoryDbEntity>

}