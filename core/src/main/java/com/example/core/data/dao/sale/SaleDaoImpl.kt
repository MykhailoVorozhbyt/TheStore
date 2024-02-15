package com.example.core.data.dao.sale

import com.example.core.data.db.TheStoreDatabase
import com.example.core.domain.db_entity.SaleHistoryDbEntity
import javax.inject.Inject

class SaleDaoImpl @Inject constructor(
    private val db: TheStoreDatabase
) : SaleDao {
    override suspend fun getSaleHistory(): List<SaleHistoryDbEntity> =
        db.saleDao().getSaleHistory()

    override suspend fun getSaleHistory(value: String): List<SaleHistoryDbEntity> =
        db.saleDao().getSaleHistory(value)
}