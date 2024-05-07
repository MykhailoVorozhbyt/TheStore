package com.example.core.domain.db_entity.check

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CheckProductDbEntity.TABLE_NAME)
data class CheckProductDbEntity(
    @PrimaryKey
    @ColumnInfo(COLUMN_CHECK_ID) val checkId: Long,
    @ColumnInfo(COLUMN_PHOTO_URI) val photoUri: String?,
    @ColumnInfo(COLUMN_NAME) val name: String,
    @ColumnInfo(COLUMN_PRICE) val price: Double,
    @ColumnInfo(COLUMN_FULL_PRICE) val fullPrice: Double,
    @ColumnInfo(COLUMN_QUANTITY) val quantity: Double,
    @ColumnInfo(COLUMN_MEASUREMENT_ID) val measurementId: Int,
    @ColumnInfo(COLUMN_CURRENCY_ID) val currencyId: Int
) {
    companion object {
        const val TABLE_NAME = "checkProductDbEntity"
        const val COLUMN_CHECK_ID = "checkId"
        const val COLUMN_PHOTO_URI = "photoUri"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_FULL_PRICE = "fullPrice"
        const val COLUMN_QUANTITY = "quantity"
        const val COLUMN_MEASUREMENT_ID = "measurementId"
        const val COLUMN_CURRENCY_ID = "currencyId"
    }
}