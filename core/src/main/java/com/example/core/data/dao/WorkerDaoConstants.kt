package com.example.core.data.dao

import com.example.core.domain.models.db_entity.WorkerEntity

const val GET_WORKERS_BY_ID =
    "SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE ${WorkerEntity.COLUMN_PASSWORD} = :id"
const val GET_WORKERS_BY_NAME = "SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE name = :name"
const val GET_WORKERS_BY_PHONE_AND_PASSWORD =
    "SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE ${WorkerEntity.COLUMN_PASSWORD} = :password AND ${WorkerEntity.COLUMN_PHONE} = :phone"