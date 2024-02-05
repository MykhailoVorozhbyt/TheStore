package com.example.core.data.repository

import com.example.core.data.dao.company.CompanyDao
import com.example.core.domain.db_entity.CompanyDbEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val workerDao: CompanyDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CompanyRepository {
    override suspend fun insertCompany(company: CompanyDbEntity): Long = withContext(ioDispatcher) {
        workerDao.insertCompany(company)
    }

    override suspend fun updateCompany(company: CompanyDbEntity) = withContext(ioDispatcher) {
        workerDao.updateCompany(company)
    }

    override suspend fun getCompanyById(id: Long): CompanyDbEntity? = withContext(ioDispatcher) {
        workerDao.getCompanyById(id)
    }
}