package com.example.core.data.repository

import com.example.core.domain.db_entity.WorkerDbEntity

interface WorkerRepository {
    suspend fun insertWorker(worker: WorkerDbEntity): Long
    suspend fun updateWorker(worker: WorkerDbEntity)
    suspend fun getWorkerByPhoneAndPassword(phone: String, password: String): WorkerDbEntity?
    suspend fun getWorkerById(id: Long): WorkerDbEntity?
    suspend fun getWorkersByName(name: String): List<WorkerDbEntity>
}