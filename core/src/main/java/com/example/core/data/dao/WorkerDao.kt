package com.example.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.constants.TableNames
import com.example.core.domain.models.db_entity.WorkerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorker(worker: WorkerEntity)

    @Query("SELECT * FROM ${TableNames.WORKER_TABLE_ENTITY} WHERE password = :password AND phone = :phone")
    suspend fun isWorkerCreated(
        password: String,
        phone: String,
    ): Flow<Boolean>
}