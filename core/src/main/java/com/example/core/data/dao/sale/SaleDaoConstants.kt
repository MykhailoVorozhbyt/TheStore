package com.example.core.data.dao.sale

import com.example.core.data.dao.SELECT_ALL_FROM
import com.example.core.domain.db_entity.SaleHistoryDbEntity

const val GET_ALL_SALE_HISTORY = "$SELECT_ALL_FROM ${SaleHistoryDbEntity.TABLE_NAME}"
const val GET_SALE_HISTORY = "$SELECT_ALL_FROM ${SaleHistoryDbEntity.TABLE_NAME} WHERE ${SaleHistoryDbEntity.COLUMN_ID} LIKE :value"