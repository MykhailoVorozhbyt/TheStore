package com.example.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.models.db_entity.WorkerEntity

@Dao
interface WorkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorker(worker: WorkerEntity)

    @Query("SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE password = :password AND phone = :phone")
    suspend fun getWorkerByPhoneAndPassword(
        password: String,
        phone: String,
    ): WorkerEntity?
}