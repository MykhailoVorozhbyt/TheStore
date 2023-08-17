package com.example.core.domain.models.db_entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = WorkerEntity.TABLE_NAME)
data class WorkerEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = COLUMN_ID) val id: Int,
    @ColumnInfo(name = COLUMN_FIRST_NAME) val firstName: String,
    @ColumnInfo(name = COLUMN_LAST_NAME) val lastName: String,
    @ColumnInfo(name = COLUMN_PASSWORD) val password: String,
    @ColumnInfo(name = COLUMN_PHONE) val phone: String,
    @ColumnInfo(name = COLUMN_MAIL) val emailAddress: String,
    @ColumnInfo(name = COLUMN_VAT_IDENTIFICATION_NUMBER) val vatIdentificationNumber: String,
) {
    companion object {
        const val TABLE_NAME = "WORKER_TABLE_ENTITY"
        const val COLUMN_ID = "id"
        const val COLUMN_FIRST_NAME = "firstName"
        const val COLUMN_LAST_NAME = "lastName"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_MAIL = "emailAddress"
        const val COLUMN_VAT_IDENTIFICATION_NUMBER = "vatIdentificationNumber"
    }
}
