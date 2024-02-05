package com.example.core.domain.db_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CompanyDbEntity.TABLE_NAME)
data class CompanyDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) val id: Long = 0,
    @ColumnInfo(name = COLUMN_PHOTO) val photoUri: String? = null,
    @ColumnInfo(name = COLUMN_NAME) val name: String,
    @ColumnInfo(name = COLUMN_DESCRIPTION) val description: String,
    @ColumnInfo(name = COLUMN_COMPANY_CREATED) val companyCreated: Boolean,
    @ColumnInfo(name = COLUMN_CREATED_AT) val createdAt: Long,
) {
    companion object {
        const val TABLE_NAME = "companyTable"
        const val COLUMN_ID = "id"
        const val COLUMN_PHOTO = "photo"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_COMPANY_CREATED = "companyCreated"
        const val COLUMN_CREATED_AT = "createdAt"
    }
}