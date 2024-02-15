package com.example.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val AppBlack = Color(0xFF222222)
val TransparentColor = Color(0x00FFFFFF)
val HintColor = Color(0xFF757575)
val ErrorColor = Color(0xFFB61919)

val Colors.whiteOrBlackColor: Color
    @Composable get() = if (isSystemInDarkTheme()) Black else White

val Colors.blackOrWhiteColor: Color
    @Composable get() = if (isSystemInDarkTheme().not()) Black else White
