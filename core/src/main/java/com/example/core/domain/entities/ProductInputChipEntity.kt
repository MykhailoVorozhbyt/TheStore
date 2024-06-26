package com.example.core.domain.entities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.theme.R

data class ProductInputChipEntity(
    val id: Long,
    @StringRes
    val textId: Int,
    @DrawableRes
    val iconId: Int
)

fun getMeasurementsChipIndexByTextId(id: Int): ProductInputChipEntity {
    return if (id == 0) {
        MeasurementsList[0]
    } else {
        MeasurementsList.find { it.textId == id }!!
    }
}

fun getCurrencyChipIndexByTextId(id: Int): ProductInputChipEntity {
    return if (id == 0) {
        CurrencyList[0]
    } else {
        CurrencyList.find { it.textId == id }!!
    }
}

val MeasurementsList = listOf(
    ProductInputChipEntity(1, R.string.kilogram, R.drawable.ic_kilogram),
    ProductInputChipEntity(2, R.string.liters, R.drawable.ic_liter),
    ProductInputChipEntity(3, R.string.milliliters, R.drawable.ic_milliliter),
    ProductInputChipEntity(4, R.string.grams, R.drawable.ic_gram),
    ProductInputChipEntity(5, R.string.gallons, R.drawable.ic_gallon),
    ProductInputChipEntity(6, R.string.portion, R.drawable.ic_portion),
    ProductInputChipEntity(
        7, R.string.single_item, R.drawable.ic_portion
    ),
)

fun getMeasurementById(id: Long): ProductInputChipEntity = MeasurementsList.find { it.id == id }!!

val CurrencyList = listOf(
    ProductInputChipEntity(1, R.string.uah, R.drawable.ic_currency_uah),
    ProductInputChipEntity(2, R.string.usd, R.drawable.ic_currency_usd),
    ProductInputChipEntity(3, R.string.euro, R.drawable.ic_currency_euro),
    ProductInputChipEntity(4, R.string.franc, R.drawable.ic_currency_franc),
    ProductInputChipEntity(5, R.string.bitcoin, R.drawable.ic_currency_bitcoin),
)
fun getCurrencyById(id: Long): ProductInputChipEntity = CurrencyList.find { it.id == id }!!
