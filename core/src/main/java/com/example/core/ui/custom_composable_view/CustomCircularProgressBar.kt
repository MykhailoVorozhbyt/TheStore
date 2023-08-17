package com.example.core.ui.custom_composable_view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
private fun CustomCircularProgressBar(
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Dp
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = strokeWidth
    )

}