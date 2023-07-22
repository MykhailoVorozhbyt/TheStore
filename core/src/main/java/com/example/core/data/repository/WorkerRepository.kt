package com.example.core.data.repository

import com.example.core.data.dao.WorkerDao
import com.example.core.domain.models.db_entity.WorkerEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkerRepository @Inject constructor(
    private val workerDao: WorkerDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insertWorker(worker: WorkerEntity) = withContext(ioDispatcher) {
        workerDao.insertWorker(worker)
    }

    suspend fun getWorkerByPhoneAndPassword(password: String, phone: String): WorkerEntity? =
        workerDao.getWorkerByPhoneAndPassword(password, phone)
}