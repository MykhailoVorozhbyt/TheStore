package com.example.core.utils.extensions.modifiers

import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

fun Modifier.inputTextFieldBorder() =
    composed {
        this.border(
            width = 5.dp, brush = Brush.horizontalGradient(
                listOf(
                    colorResource(com.example.theme.R.color.white)
                )
            ), shape = BaseRoundedCornerShape()
        )
    }
