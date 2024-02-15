package com.example.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val WhiteTextStyle
    @Composable get() = TextStyle(
        color = TheStoreColors.whiteOrBlackColor,
    )


val BlackTextStyle: TextStyle
    @Composable get() = TextStyle(
        color = TheStoreColors.blackOrWhiteColor,
    )

val WhiteBoldTextStyle: TextStyle
    @Composable get() = TextStyle(
        color = TheStoreColors.whiteOrBlackColor,
        fontWeight = FontWeight.Bold
    )

val BlackBoldTextStyle: TextStyle
    @Composable get() = TextStyle(
        color = TheStoreColors.blackOrWhiteColor,
        fontWeight = FontWeight.Bold
    )

@Composable
fun BlackBoldTextStyle(size: Int): TextStyle = TextStyle(
    color = TheStoreColors.blackOrWhiteColor,
    fontWeight = FontWeight.Bold,
    fontSize = size.sp
)
