//package com.example.core.domain.db_entity
//
//import androidx.annotation.DrawableRes
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity(tableName = MeasurementsDbEntity.TABLE_NAME)
//data class MeasurementsDbEntity(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = COLUMN_ID) val id: Long,
//    @ColumnInfo(name = COLUMN_NAME) val name: String,
//    @DrawableRes
//    @ColumnInfo(name = COLUMN_ICON_ID) val iconId: Int
//) {
//    companion object {
//        const val TABLE_NAME = "MEASUREMENTS_TABLE_ENTITY"
//        const val COLUMN_ID = "id"
//        const val COLUMN_NAME = "name"
//        const val COLUMN_ICON_ID = "iconId"
//    }
//}