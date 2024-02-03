package com.example.core.data.dao.worker

import com.example.core.data.dao.DELETE_FROM
import com.example.core.data.dao.SELECT_ALL_FROM
import com.example.core.domain.db_entity.WorkerDbEntity

private const val WORKER_ACCESS =
    "${WorkerDbEntity.COLUMN_ACCESS} = 2"

const val GET_ALL_WORKERS =
    "$SELECT_ALL_FROM ${WorkerDbEntity.TABLE_NAME} WHERE $WORKER_ACCESS"

const val GET_WORKERS_BY_ID =
    "$SELECT_ALL_FROM ${WorkerDbEntity.TABLE_NAME} WHERE ${WorkerDbEntity.COLUMN_ID} = :id"

const val GET_WORKERS_BY_CHARACTER =
    "$SELECT_ALL_FROM ${WorkerDbEntity.TABLE_NAME} " +
            "WHERE $WORKER_ACCESS " +
            "AND ${WorkerDbEntity.COLUMN_NAME} LIKE :character " +
            "OR ${WorkerDbEntity.COLUMN_SURNAME} LIKE :character"

const val GET_WORKERS_BY_PHONE_AND_PASSWORD =
    "$SELECT_ALL_FROM ${WorkerDbEntity.TABLE_NAME} WHERE " +
            "${WorkerDbEntity.COLUMN_PASSWORD} = :password AND " +
            "${WorkerDbEntity.COLUMN_PHONE} = :phone"


const val DELETE_EMPLOYER_BY_ID =
    "$DELETE_FROM ${WorkerDbEntity.TABLE_NAME} WHERE ${WorkerDbEntity.COLUMN_ID} = :id"