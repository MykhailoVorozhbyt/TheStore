package com.example.core.utils.extensions.modifiers.fab_border_modifier

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.fabBorderModifier(): Modifier =
    this.border(
        width = 2.dp,
        color = Color.Black,
        shape = CircleShape
    )