package com.example.core.data.dao

import com.example.core.domain.db_entity.WorkerDbEntity

private const val WORKER_ACCESS =
    "${WorkerDbEntity.COLUMN_ACCESS} = 2"

const val GET_WORKERS =
    "SELECT * FROM ${WorkerDbEntity.TABLE_NAME} WHERE $WORKER_ACCESS"
const val GET_WORKERS_BY_ID =
    "SELECT * FROM ${WorkerDbEntity.TABLE_NAME} WHERE ${WorkerDbEntity.COLUMN_ID} = :id"

const val GET_WORKERS_BY_CHARACTER = "SELECT * FROM ${WorkerDbEntity.TABLE_NAME} " +
        "WHERE ${WorkerDbEntity.COLUMN_NAME} LIKE :character " +
        "OR ${WorkerDbEntity.COLUMN_SURNAME} LIKE :character " +
        "AND $WORKER_ACCESS"

const val GET_WORKERS_BY_PHONE_AND_PASSWORD =
    "SELECT * FROM ${WorkerDbEntity.TABLE_NAME} WHERE ${WorkerDbEntity.COLUMN_PASSWORD} = :password AND ${WorkerDbEntity.COLUMN_PHONE} = :phone"