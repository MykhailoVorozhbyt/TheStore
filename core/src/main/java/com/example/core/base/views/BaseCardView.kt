package com.example.core.base.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.utils.extensions.modifiers.defaultCardPadding
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor

@Composable
fun BaseCardView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .defaultCardPadding(),
        colors = CardDefaults.cardColors(
            containerColor = TheStoreColors.blackOrWhiteColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        content.invoke()
    }
}