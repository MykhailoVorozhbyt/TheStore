package com.example.core.domain.db_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SaleHistoryDbEntity.TABLE_NAME)
data class SaleHistoryDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) val id: Long = 0,
    @ColumnInfo(name = COLUMN_SALE_ID) val saleId: Long,
    @ColumnInfo(name = COLUMN_CREATED_AT) val createdAt: Long,
    @ColumnInfo(name = COLUMN_FULL_PRICE) val fullPrice: Double,
    @ColumnInfo(name = COLUMN_PRODUCTS) val products: Int,
) {
    companion object {
        const val TABLE_NAME = "sale_history_table"
        const val COLUMN_ID = "id"
        const val COLUMN_SALE_ID = "sale_id"
        const val COLUMN_CREATED_AT = "created_at"
        const val COLUMN_FULL_PRICE = "full_price"
        const val COLUMN_PRODUCTS = "products"
    }
}