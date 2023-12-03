package com.example.core.data.dao

import com.example.core.data.db.TheStoreDatabase
import com.example.core.domain.models.db_entity.WorkerEntity
import javax.inject.Inject

class WorkerDaoImpl
@Inject constructor(
    private val db: TheStoreDatabase
) : WorkerDao {
    override suspend fun insertWorker(worker: WorkerEntity): Long =
        db.workerDao().insertWorker(worker)

    override suspend fun getWorkerByPhoneAndPassword(
        phone: String, password: String
    ): WorkerEntity? = db.workerDao().getWorkerByPhoneAndPassword(phone, password)

    override suspend fun getWorkerById(id: Long): WorkerEntity? = db.workerDao().getWorkerById(id)
    override suspend fun getWorkersByName(name: String): List<WorkerEntity> {
        TODO("Not yet implemented")
    }

}