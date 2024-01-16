package com.example.core.utils.extensions.modifiers

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


fun Modifier.loginIconSize(): Modifier =
    this.size(140.dp)

fun Modifier.defaultIconSize(): Modifier =
    this.size(30.dp)

fun Modifier.defaultListIconSize(): Modifier =
    this.size(80.dp)
