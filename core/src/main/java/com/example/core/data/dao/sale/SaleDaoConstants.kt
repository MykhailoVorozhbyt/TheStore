package com.example.core.data.dao.sale

import com.example.core.data.dao.SELECT_ALL_FROM
import com.example.core.domain.db_entity.SaleHistoryDbEntity
import com.example.core.domain.db_entity.check.CheckDbEntity
import com.example.core.domain.db_entity.check.CheckProductDbEntity

const val GET_SALE_CHECK_BY_ID =
    "$SELECT_ALL_FROM ${CheckDbEntity.TABLE_NAME} WHERE ${CheckDbEntity.COLUMN_ID} = :id"


const val GET_CHECK_PRODUCT_BY_SALE_CHECK_ID =
    "$SELECT_ALL_FROM ${CheckProductDbEntity.TABLE_NAME} WHERE ${CheckProductDbEntity.COLUMN_CHECK_ID} = :id"

const val GET_ALL_SALE_HISTORY = "$SELECT_ALL_FROM ${SaleHistoryDbEntity.TABLE_NAME}"
const val GET_SALE_HISTORY =
    "$SELECT_ALL_FROM ${SaleHistoryDbEntity.TABLE_NAME} WHERE ${SaleHistoryDbEntity.COLUMN_ID} LIKE :value"