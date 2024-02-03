package com.example.core.data.dao.worker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.core.domain.db_entity.WorkerDbEntity

@Dao
interface WorkerDao {

    @Insert
    suspend fun insertWorker(worker: WorkerDbEntity): Long

    @Update
    suspend fun updateWorker(worker: WorkerDbEntity)

    @Query(DELETE_EMPLOYER_BY_ID)
    suspend fun deleteEmployerById(id: Long)

    @Query(GET_WORKERS_BY_PHONE_AND_PASSWORD)
    suspend fun getWorkerByPhoneAndPassword(
        phone: String, password: String
    ): WorkerDbEntity?

    @Query(GET_WORKERS_BY_ID)
    suspend fun getWorkerById(
        id: Long
    ): WorkerDbEntity?

    @Query(GET_WORKERS_BY_CHARACTER)
    suspend fun getWorkersByName(character: String): List<WorkerDbEntity>

    @Query(GET_ALL_WORKERS)
    suspend fun getWorkers(): List<WorkerDbEntity>

}