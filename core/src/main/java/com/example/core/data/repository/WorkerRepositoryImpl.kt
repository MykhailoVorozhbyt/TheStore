package com.example.core.data.repository

import com.example.core.data.dao.WorkerDao
import com.example.core.domain.db_entity.WorkerDbEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkerRepositoryImpl
@Inject constructor(
    private val workerDao: WorkerDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : WorkerRepository {
    override suspend fun insertWorker(worker: WorkerDbEntity): Long = withContext(ioDispatcher) {
        workerDao.insertWorker(worker)
    }

    override suspend fun updateWorker(worker: WorkerDbEntity) =
        workerDao.updateWorker(worker)

    override suspend fun getWorkerByPhoneAndPassword(
        phone: String,
        password: String
    ): WorkerDbEntity? =
        workerDao.getWorkerByPhoneAndPassword(password, phone)

    override suspend fun getWorkerById(id: Long): WorkerDbEntity? =
        workerDao.getWorkerById(id)

    override suspend fun getWorkersByName(name: String): List<WorkerDbEntity> {
        if (name.isEmpty()){
            return workerDao.getWorkers()
        }
        return workerDao.getWorkersByName(name)
    }


}

