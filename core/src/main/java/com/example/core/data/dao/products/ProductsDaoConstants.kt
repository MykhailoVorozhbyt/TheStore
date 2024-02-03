package com.example.core.data.dao.products

import com.example.core.data.dao.DELETE_FROM
import com.example.core.data.dao.SELECT_ALL_FROM
import com.example.core.domain.db_entity.ProductDbEntity

private const val SORT_BY_CREATED_AT = "ORDER BY ${ProductDbEntity.COLUMN_CREATED_AT} DESC"


const val GET_ALL_PRODUCTS =
    "$SELECT_ALL_FROM ${ProductDbEntity.TABLE_NAME} $SORT_BY_CREATED_AT"

const val GET_PRODUCTS_BY_NAME =
    "$SELECT_ALL_FROM ${ProductDbEntity.TABLE_NAME} WHERE " +
            "${ProductDbEntity.COLUMN_NAME} LIKE :name $SORT_BY_CREATED_AT"

const val GET_PRODUCT_BY_ID =
    "$SELECT_ALL_FROM ${ProductDbEntity.TABLE_NAME} WHERE ${ProductDbEntity.COLUMN_ID} = :id"

const val DELETE_PRODUCT_BY_ID =
    "$DELETE_FROM ${ProductDbEntity.TABLE_NAME} WHERE ${ProductDbEntity.COLUMN_ID} = :id"
