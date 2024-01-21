package com.example.core.data.dao.worker

import com.example.core.data.db.TheStoreDatabase
import com.example.core.domain.db_entity.WorkerDbEntity
import javax.inject.Inject

class WorkerDaoImpl
@Inject constructor(
    private val db: TheStoreDatabase
) : WorkerDao {
    override suspend fun insertWorker(worker: WorkerDbEntity): Long =
        db.workerDao().insertWorker(worker)

    override suspend fun updateWorker(worker: WorkerDbEntity) =
        db.workerDao().updateWorker(worker)


    override suspend fun getWorkerByPhoneAndPassword(
        phone: String, password: String
    ): WorkerDbEntity? = db.workerDao().getWorkerByPhoneAndPassword(phone, password)

    override suspend fun getWorkerById(id: Long): WorkerDbEntity? = db.workerDao().getWorkerById(id)
    override suspend fun getWorkersByName(character: String): List<WorkerDbEntity> =
        db.workerDao().getWorkersByName(character)

    override suspend fun getWorkers(): List<WorkerDbEntity> =
        db.workerDao().getWorkers()

}