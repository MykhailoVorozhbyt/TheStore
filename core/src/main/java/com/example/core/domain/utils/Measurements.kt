package com.example.core.domain.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.core.domain.MeasurementDelegate
import com.example.theme.R

enum class Measurements(
    override val id: Long,
    @get:StringRes
    override val textId: Int,
    @get:DrawableRes
    override val iconId: Int
) : MeasurementDelegate {
    KILOGRAM(1, R.string.kilogram, R.drawable.ic_kilogram),
    LITER(2, R.string.liters, R.drawable.ic_liter),
    MILLILITER(3, R.string.milliliters, R.drawable.ic_milliliter),
    GRAM(4, R.string.grams, R.drawable.ic_gram),
    GALLON(5, R.string.gallons, R.drawable.ic_gallon),
    PORTION(6, R.string.portion, R.drawable.ic_portion),
    SINGLE_ITEM(7, R.string.single_item, R.drawable.ic_portion);

    companion object {
        fun getMeasurementsChipIndexByTextId(id: Int) =
            MeasurementsList.firstOrNull { it.textId == id } ?: KILOGRAM

        fun getMeasurementById(id: Long) = MeasurementsList.firstOrNull { it.id == id } ?: KILOGRAM
    }
}

val MeasurementsList = listOf(
    Measurements.KILOGRAM,
    Measurements.LITER,
    Measurements.MILLILITER,
    Measurements.GRAM,
    Measurements.GALLON,
    Measurements.PORTION,
    Measurements.SINGLE_ITEM
)

