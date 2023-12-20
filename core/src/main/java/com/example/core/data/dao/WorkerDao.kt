package com.example.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.domain.models.db_entity.WorkerEntity

@Dao
interface WorkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorker(worker: WorkerEntity): Long

    @Update
    suspend fun updateWorker(worker: WorkerEntity)

    @Query(GET_WORKERS_BY_PHONE_AND_PASSWORD)
    suspend fun getWorkerByPhoneAndPassword(
        phone: String, password: String
    ): WorkerEntity?

    @Query(GET_WORKERS_BY_ID)
    suspend fun getWorkerById(
        id: Long
    ): WorkerEntity?

    @Query(GET_WORKERS_BY_NAME)
    suspend fun getWorkersByName(name: String): List<WorkerEntity>

}