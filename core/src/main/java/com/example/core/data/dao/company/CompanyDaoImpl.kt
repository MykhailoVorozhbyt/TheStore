package com.example.core.data.dao.company

import com.example.core.data.db.TheStoreDatabase
import com.example.core.domain.db_entity.CompanyDbEntity
import javax.inject.Inject

class CompanyDaoImpl @Inject constructor(
    private val db: TheStoreDatabase
) : CompanyDao {
    override suspend fun insertCompany(company: CompanyDbEntity): Long =
        db.companyDao().insertCompany(company)

    override suspend fun updateCompany(company: CompanyDbEntity) =
        db.companyDao().updateCompany(company)

    override suspend fun getCompanyById(id: Long): CompanyDbEntity? =
        db.companyDao().getCompanyById(id)

}