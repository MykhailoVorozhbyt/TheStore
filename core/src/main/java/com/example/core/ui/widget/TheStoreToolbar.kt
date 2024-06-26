package com.example.core.ui.widget

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.WhiteBoldTextStyle
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor

@PreviewLightDark
@Composable
fun TheStoreToolbarPreview() {
    TheStoreOnBackCenterAlignedTopAppBar(titleResId = R.string.app_name) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheStoreOnBackCenterAlignedTopAppBar(
    @StringRes titleResId: Int,
    pressOnBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(titleResId),
                style = WhiteBoldTextStyle
            )
        },
        navigationIcon = {
            IconButton(onClick = { pressOnBack.invoke() }) {
                Icon(
                    rememberVectorPainter(Icons.Filled.ArrowBack),
                    contentDescription = null,
                    tint = TheStoreColors.whiteOrBlackColor,
                    modifier = Modifier.padding(8.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = TheStoreColors.blackOrWhiteColor
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@PreviewLightDark
@Composable
fun TheStoreOnActionToolbarPreview() {
    TheStoreOnActionToolbar(
        titleResId = R.string.worker,
        pressOnBack = { },
        pressEditCreate = { }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheStoreOnActionToolbar(
    @StringRes titleResId: Int,
    pressOnBack: () -> Unit,
    pressEditCreate: () -> Unit,
) {
    val bgColor = TheStoreColors.blackOrWhiteColor
    val itemsColor = TheStoreColors.whiteOrBlackColor
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(titleResId),
                textAlign = TextAlign.Center,
                color = itemsColor,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        navigationIcon = {
            Icon(
                rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = null,
                tint = itemsColor,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { pressOnBack.invoke() }
            )
        },
        actions = {
            Icon(
                rememberVectorPainter(Icons.Filled.Check),
                contentDescription = null,
                tint = itemsColor,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { pressEditCreate.invoke() }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = bgColor
        ),
        modifier = Modifier.fillMaxWidth()
    )
}