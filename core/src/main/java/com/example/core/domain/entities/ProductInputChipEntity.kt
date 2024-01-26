package com.example.core.domain.entities

import androidx.annotation.DrawableRes

data class ProductInputChipEntity(
    val id: Long,
    val name: String,
    @DrawableRes
    val iconId: Int
)

val MeasurementsList = listOf(
    ProductInputChipEntity(1, "Kilogram", com.example.theme.R.drawable.ic_kilogram),
    ProductInputChipEntity(2, "Liters", com.example.theme.R.drawable.ic_liter),
    ProductInputChipEntity(3, "Milliliters", com.example.theme.R.drawable.ic_milliliter),
    ProductInputChipEntity(4, "Grams", com.example.theme.R.drawable.ic_gram),
    ProductInputChipEntity(5, "Gallons", com.example.theme.R.drawable.ic_gallon),
    ProductInputChipEntity(6, "Portion", com.example.theme.R.drawable.ic_portion),
)

val CurrencyList = listOf(
    ProductInputChipEntity(1, "UAH", com.example.theme.R.drawable.ic_currency_franc),
    ProductInputChipEntity(5, "Bitcoin", com.example.theme.R.drawable.ic_currency_bitcoin),
    ProductInputChipEntity(6, "Franc", com.example.theme.R.drawable.ic_currency_franc),
)
