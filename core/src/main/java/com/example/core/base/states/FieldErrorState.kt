package com.example.core.base.states

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class FieldErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = com.example.theme.R.string.empty_string
) : Parcelable