package com.example.core.domain.entities

import androidx.annotation.DrawableRes

data class CurrencyEntity(
    val id: Long,
    val name: String,
    @DrawableRes
    val iconId: Int
)
