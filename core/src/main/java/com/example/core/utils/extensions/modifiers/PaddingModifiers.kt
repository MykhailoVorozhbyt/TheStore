package com.example.core.utils.extensions.modifiers

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.iconPadding(): Modifier =
    this.padding(20.dp)

fun Modifier.defaultPadding(): Modifier =
    this.padding(20.dp)

fun Modifier.defaultStartPadding(): Modifier =
    this.padding(start = 20.dp)

fun Modifier.defaultTopPadding(): Modifier =
    this.padding(top = 20.dp)

fun Modifier.defaultEndPadding(): Modifier =
    this.padding(end = 20.dp)

fun Modifier.defaultBottomPadding(): Modifier =
    this.padding(bottom = 20.dp)

fun Modifier.defaultTextStartPadding(): Modifier =
    this.padding(start = 6.dp)

fun Modifier.defaultTextTopPadding(): Modifier =
    this.padding(top = 6.dp)

fun Modifier.defaultTextEndPadding(): Modifier =
    this.padding(end = 6.dp)

fun Modifier.defaultTextBottomPadding(): Modifier =
    this.padding(bottom = 6.dp)

fun Modifier.loginIconSize(): Modifier =
    this.size(140.dp)

fun Modifier.defaultCardPadding(): Modifier =
    this.padding(16.dp)