package com.example.core.base.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.core.domain.constants.Constants.BASE_CARD_VIEW_HEIGHT
import com.example.core.utils.extensions.modifiers.defaultCardPadding
import com.example.core.utils.extensions.modifiers.drawColoredShadow
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor

@Composable
fun BaseCardView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(BASE_CARD_VIEW_HEIGHT.dp)
            .drawColoredShadow(
                color = TheStoreColors.blackOrWhiteColor,
                shadowRadius = 10.dp
            )
            .defaultCardPadding(),
        colors = CardDefaults.cardColors(
            containerColor = TheStoreColors.whiteOrBlackColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content.invoke()
        }
    }
}