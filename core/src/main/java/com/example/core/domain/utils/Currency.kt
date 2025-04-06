package com.example.core.domain.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.core.domain.MeasurementDelegate
import com.example.theme.R

enum class Currency(
    override val id: Long,
    @get:StringRes
    override val textId: Int,
    @get:DrawableRes
    override val iconId: Int
) : MeasurementDelegate {
    UAH(1, R.string.uah, R.drawable.ic_currency_uah),
    USD(2, R.string.usd, R.drawable.ic_currency_usd),
    EURO(3, R.string.euro, R.drawable.ic_currency_euro),
    FRANC(4, R.string.franc, R.drawable.ic_currency_franc),
    BITCOIN(5, R.string.bitcoin, R.drawable.ic_currency_bitcoin);

    companion object {
        fun getCurrencyById(id: Long): Currency = CurrencyList.firstOrNull { it.id == id } ?: UAH
        fun getCurrencyChipIndexByTextId(id: Int) =
            CurrencyList.firstOrNull { it.textId == id } ?: UAH
    }

}

val CurrencyList =
    listOf(Currency.UAH, Currency.USD, Currency.EURO, Currency.FRANC, Currency.BITCOIN)