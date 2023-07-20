package com.example.core.data.repository

import com.example.core.data.dao.WorkerDao
import com.example.core.domain.models.db_entity.WorkerEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkerRepository @Inject constructor(
    private val workerDao: WorkerDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insertWorker(worker: WorkerEntity) = withContext(ioDispatcher) {
        workerDao.insertWorker(worker)
    }

    suspend fun isWorkerCreated(
        password: String,
        phone: String,
    ): Flow<Boolean> = withContext(ioDispatcher) {
        return@withContext workerDao.isWorkerCreated(
            password, phone
        )
    }
}