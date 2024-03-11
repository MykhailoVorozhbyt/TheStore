package com.example.core.data.repository

import com.example.core.data.dao.sale.SaleDao
import com.example.core.domain.db_entity.SaleHistoryDbEntity
import com.example.core.domain.db_entity.check.CheckDbEntity
import com.example.core.domain.db_entity.check.CheckProductDbEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaleRepositoryImpl @Inject constructor(
    private val saleDao: SaleDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SaleRepository {
    override suspend fun saleCheck(check: CheckDbEntity): Long = withContext(ioDispatcher) {
        saleDao.saleCheck(check)
    }

    override suspend fun updateSaleCheck(check: CheckDbEntity) = withContext(ioDispatcher) {
        saleDao.updateSaleCheck(check)
    }

    override suspend fun getSaleCheckById(id: Long): CheckDbEntity? =
        withContext(ioDispatcher) {
            saleDao.getSaleCheckById(id)
        }

    override suspend fun insertCheckProduct(list: List<CheckProductDbEntity>) =
        withContext(ioDispatcher){
            saleDao.insertCheckProduct(list)
        }

    override suspend fun getCheckProductBySaleCheckId(saleCheckId: Long): List<CheckProductDbEntity> =
        withContext(ioDispatcher){
            saleDao.getCheckProductBySaleCheckId(saleCheckId)
        }

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