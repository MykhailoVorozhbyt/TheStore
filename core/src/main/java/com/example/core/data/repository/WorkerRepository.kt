package com.example.core.data.repository

import com.example.core.domain.models.db_entity.WorkerEntity

interface WorkerRepository {
    suspend fun insertWorker(worker: WorkerEntity)
    suspend fun getWorkerByPhoneAndPassword(phone: String, password: String): WorkerEntity?
}