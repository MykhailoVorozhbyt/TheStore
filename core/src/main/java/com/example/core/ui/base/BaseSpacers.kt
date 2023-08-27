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

@Composable
fun HorizontalSpacerColorView(color: Color) = Box(
    modifier = Modifier
        .background(color, baseRoundedCornerShape())
        .height(4.dp)
        .fillMaxWidth()
)

@Composable
fun DefaultHorizontalSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(4.dp)
)

@Composable
fun ExtraSmallHorizontalSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(6.dp)
)

@Composable
fun SmallSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(12.dp)
)

@Composable
fun MediumSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(18.dp)
)

@Composable
fun LargeSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(24.dp)
)

@Composable
fun ExtraLargeSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(30.dp)
)