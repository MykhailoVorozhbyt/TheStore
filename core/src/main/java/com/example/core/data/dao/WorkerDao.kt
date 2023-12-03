package com.example.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.models.db_entity.WorkerEntity

@Dao
interface WorkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorker(worker: WorkerEntity): Long

    @Query("SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE ${WorkerEntity.COLUMN_PASSWORD} = :password AND ${WorkerEntity.COLUMN_PHONE} = :phone")
    suspend fun getWorkerByPhoneAndPassword(
        phone: String, password: String
    ): WorkerEntity?

    @Query("SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE ${WorkerEntity.COLUMN_PASSWORD} = :id")
    suspend fun getWorkerById(
        id: Long
    ): WorkerEntity?

    @Query(GET_WORKERS_BY_NAME)
    suspend fun getWorkersByName(name: String): List<WorkerEntity>

}