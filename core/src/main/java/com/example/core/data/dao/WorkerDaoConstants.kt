package com.example.core.data.dao

import com.example.core.domain.models.db_entity.WorkerEntity

const val GET_WORKERS =
    "SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE ${WorkerEntity.COLUMN_IS_OWNER} = false"
const val GET_WORKERS_BY_ID =
    "SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE ${WorkerEntity.COLUMN_PASSWORD} = :id"

const val GET_WORKERS_BY_CHARACTER = "SELECT * FROM ${WorkerEntity.TABLE_NAME} " +
        "WHERE ${WorkerEntity.COLUMN_NAME} LIKE :character " +
        "OR ${WorkerEntity.COLUMN_SURNAME} LIKE :character " +
        "AND ${WorkerEntity.COLUMN_IS_OWNER} = false"

const val GET_WORKERS_BY_PHONE_AND_PASSWORD =
    "SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE ${WorkerEntity.COLUMN_PASSWORD} = :password AND ${WorkerEntity.COLUMN_PHONE} = :phone"