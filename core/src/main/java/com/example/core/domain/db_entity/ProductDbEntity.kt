package com.example.core.domain.db_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProductDbEntity.TABLE_NAME)
data class ProductDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) val id: Long = 0,
    @ColumnInfo(name = COLUMN_PHOTO) val photoUri: String? = null,
    @ColumnInfo(name = COLUMN_NAME) val name: String,
    @ColumnInfo(name = COLUMN_PRICE) val price: Double,
    @ColumnInfo(name = COLUMN_MEASUREMENT_ID) val measurementId: Long,
    @ColumnInfo(name = COLUMN_DESCRIPTION) val description: String,
    @ColumnInfo(name = COLUMN_BARCODE) val barcode: String,
    @ColumnInfo(name = COLUMN_CREATED_AT) val createdAt: Long,
) {
    companion object {
        const val TABLE_NAME = "PRODUCTS_TABLE_ENTITY"
        const val COLUMN_ID = "id"
        const val COLUMN_PHOTO = "photo"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_MEASUREMENT_ID = "measurementId"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_BARCODE = "barcode"
        const val COLUMN_CREATED_AT = "createdAt"
    }
}
