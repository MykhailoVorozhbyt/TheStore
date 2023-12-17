package com.example.core.data.dao

import com.example.core.domain.models.db_entity.WorkerEntity

const val GET_WORKERS_BY_NAME = "SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE name = :name"