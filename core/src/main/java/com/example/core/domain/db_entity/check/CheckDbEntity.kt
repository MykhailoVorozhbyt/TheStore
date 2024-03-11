package com.example.core.domain.db_entity.check

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CheckDbEntity.TABLE_NAME)
data class CheckDbEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID) val id: Long = 0,
    @ColumnInfo(name = COLUMN_CHECK_PRICE) val checkPrice: Double,
    @ColumnInfo(name = COLUMN_CREATED_AT) val createdAt: Long,
    @ColumnInfo(name = COLUMN_IS_CANCELED) val isCanceled: Boolean
) {
    companion object {
        const val TABLE_NAME = "saleCheckDbEntity"
        const val COLUMN_ID = "id"
        const val COLUMN_CHECK_PRICE = "checkPrice"
        const val COLUMN_CREATED_AT = "createdAt"
        const val COLUMN_IS_CANCELED = "isCanceled"
    }
}