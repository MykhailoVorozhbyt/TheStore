package com.example.core.utils.type_converters

import androidx.room.TypeConverter
import com.example.core.domain.db_entity.check.CheckProductDbEntity
import com.example.core.utils.extensions.fromJson
import com.example.core.utils.extensions.json

class CheckProductDbConverter {
    @TypeConverter
    fun fromCheckProductList(value: List<CheckProductDbEntity>): String = value.json()

    @TypeConverter
    fun toCheckProductList(value: String): List<CheckProductDbEntity> = value.fromJson()
}
