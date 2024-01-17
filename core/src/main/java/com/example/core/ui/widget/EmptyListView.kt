package com.example.core.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.whiteOrBlackColor

@Composable
fun EmptyListView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_list),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = TheStoreColors.whiteOrBlackColor
        )
        Text(
            text = stringResource(id = R.string.empty_list),
            color = TheStoreColors.whiteOrBlackColor,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}