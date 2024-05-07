package com.example.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.dao.company.CompanyDao
import com.example.core.data.dao.products.ProductsDao
import com.example.core.data.dao.sale.SaleDao
import com.example.core.data.dao.worker.WorkerDao
import com.example.core.domain.db_entity.CompanyDbEntity
import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.db_entity.check.CheckDbEntity
import com.example.core.domain.db_entity.SaleHistoryDbEntity
import com.example.core.domain.db_entity.WorkerDbEntity
import com.example.core.domain.db_entity.check.CheckProductDbEntity
import com.example.core.utils.type_converters.CheckProductDbConverter

@Database(
    entities = [
        WorkerDbEntity::class,
        ProductDbEntity::class,
        CompanyDbEntity::class,
        SaleHistoryDbEntity::class,
        CheckDbEntity::class,
        CheckProductDbEntity::class,
    ],
    version = 9,
    exportSchema = false
)
@TypeConverters(CheckProductDbConverter::class)
abstract class TheStoreDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
    abstract fun productsDao(): ProductsDao
    abstract fun companyDao(): CompanyDao
    abstract fun saleDao(): SaleDao
}