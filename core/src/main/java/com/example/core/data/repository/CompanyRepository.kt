package com.example.core.data.repository

import com.example.core.domain.db_entity.CompanyDbEntity

interface CompanyRepository {
    suspend fun insertCompany(company: CompanyDbEntity): Long
    suspend fun updateCompany(company: CompanyDbEntity)
    suspend fun getCompanyById(id: Long): CompanyDbEntity?
}