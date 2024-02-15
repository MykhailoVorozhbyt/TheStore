package com.example.core.data.repository

import com.example.core.data.dao.sale.SaleDao
import com.example.core.domain.db_entity.SaleHistoryDbEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaleRepositoryImpl @Inject constructor(
    private val saleDao: SaleDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SaleRepository {
    override suspend fun getSaleHistory(): List<SaleHistoryDbEntity> =
        withContext(ioDispatcher) {
            saleDao.getSaleHistory()
        }

    override suspend fun getSaleHistory(value: String): List<SaleHistoryDbEntity> =
        withContext(ioDispatcher) {
            if (value.isEmpty()) {
                return@withContext saleDao.getSaleHistory()
            }
            saleDao.getSaleHistory(value)
        }
}