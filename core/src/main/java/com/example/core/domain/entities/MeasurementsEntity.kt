package com.example.core.domain.entities

import androidx.annotation.DrawableRes

data class MeasurementsEntity(
    val id: Long,
    val name: String,
    @DrawableRes
    val iconId: Int
)
