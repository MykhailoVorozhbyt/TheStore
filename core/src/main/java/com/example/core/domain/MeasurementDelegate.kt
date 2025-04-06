package com.example.core.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface MeasurementDelegate {
    val id: Long
    @get:StringRes
    val textId: Int
    @get:DrawableRes
    val iconId: Int
}