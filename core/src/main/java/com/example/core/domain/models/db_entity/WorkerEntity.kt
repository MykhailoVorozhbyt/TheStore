package com.example.core.domain.models.db_entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.domain.constants.TableNames

@Entity(tableName = TableNames.WORKER_TABLE_ENTITY)
data class WorkerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val firstName: String,
    val lastName: String,
    val password: String,
    val phone: String,
)
