package com.example.core.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.baseSpacerHeight

@Composable
fun BaseSpacer() = Spacer(
    modifier = Modifier
        .height(baseSpacerHeight())
        .fillMaxWidth()
)

@Composable
fun BaseSpacerColorView(color: Color) = Box(
    modifier = Modifier
        .background(color, baseRoundedCornerShape())
        .height(4.dp)
        .fillMaxWidth()
)
