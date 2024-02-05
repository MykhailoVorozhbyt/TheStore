package com.example.core.data.dao.company

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.core.data.dao.SELECT_ALL_FROM
import com.example.core.domain.db_entity.CompanyDbEntity

@Dao
interface CompanyDao {
    @Insert
    suspend fun insertCompany(company: CompanyDbEntity): Long

    @Update
    suspend fun updateCompany(company: CompanyDbEntity)

    @Query("$SELECT_ALL_FROM ${CompanyDbEntity.TABLE_NAME} WHERE ${CompanyDbEntity.COLUMN_ID} = :id")
    suspend fun getCompanyById(
        id: Long
    ): CompanyDbEntity?

}