package com.example.core.domain.entities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ProductInputChipEntity(
    val id: Long,
    @StringRes
    val textId: Int,
    @DrawableRes
    val iconId: Int
)