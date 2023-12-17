package com.example.core.data.repository

import com.example.core.domain.models.db_entity.WorkerEntity

interface WorkerRepository {
    suspend fun insertWorker(worker: WorkerEntity): Long
    suspend fun getWorkerByPhoneAndPassword(phone: String, password: String): WorkerEntity?
    suspend fun getWorkerById(id: Long): WorkerEntity?
    suspend fun getWorkersByName(name: String): List<WorkerEntity>
}