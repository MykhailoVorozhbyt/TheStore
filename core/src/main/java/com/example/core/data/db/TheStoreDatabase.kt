package com.example.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.dao.products.ProductsDao
import com.example.core.data.dao.worker.WorkerDao
import com.example.core.domain.db_entity.ProductDbEntity
import com.example.core.domain.db_entity.WorkerDbEntity

@Database(
    entities = [WorkerDbEntity::class, ProductDbEntity::class],
    version = 2,
    exportSchema = false
)
abstract class TheStoreDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
    abstract fun productsDao(): ProductsDao
}