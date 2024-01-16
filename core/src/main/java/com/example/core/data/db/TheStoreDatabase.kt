package com.example.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.dao.WorkerDao
import com.example.core.domain.db_entity.WorkerDbEntity

//@TypeConverters(BitmapConverters::class)
@Database(entities = [WorkerDbEntity::class], version = 1, exportSchema = false)
abstract class TheStoreDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
}