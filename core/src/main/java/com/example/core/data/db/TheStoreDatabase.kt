package com.example.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.dao.WorkerDao


@Database(entities = [WorkerDao::class], version = 1, exportSchema = false)
abstract class TheStoreDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
}