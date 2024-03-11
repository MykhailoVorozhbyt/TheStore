package com.example.core.data.dao.sale

import com.example.core.data.db.TheStoreDatabase
import com.example.core.domain.db_entity.SaleHistoryDbEntity
import com.example.core.domain.db_entity.check.CheckDbEntity
import com.example.core.domain.db_entity.check.CheckProductDbEntity
import javax.inject.Inject

class SaleDaoImpl @Inject constructor(
    private val db: TheStoreDatabase
) : SaleDao {
    override suspend fun saleCheck(check: CheckDbEntity): Long = db.saleDao().saleCheck(check)

    override suspend fun getSaleCheckById(id: Long): CheckDbEntity? =
        db.saleDao().getSaleCheckById(id)

    override suspend fun updateSaleCheck(check: CheckDbEntity) =
        db.saleDao().updateSaleCheck(check)

    override suspend fun insertCheckProduct(list: List<CheckProductDbEntity>)  =
        db.saleDao().insertCheckProduct(list)

    override suspend fun getCheckProductBySaleCheckId(id: Long): List<CheckProductDbEntity>  =
        db.saleDao().getCheckProductBySaleCheckId(id)

    override suspend fun getSaleHistory(): List<SaleHistoryDbEntity> =
        db.saleDao().getSaleHistory()

    override suspend fun getSaleHistory(value: String): List<SaleHistoryDbEntity> =
        db.saleDao().getSaleHistory(value)
}