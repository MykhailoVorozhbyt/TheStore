package com.example.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.dao.WorkerDao
import com.example.core.domain.models.db_entity.WorkerEntity


@Database(entities = [WorkerEntity::class], version = 1, exportSchema = false)
abstract class TheStoreDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
}