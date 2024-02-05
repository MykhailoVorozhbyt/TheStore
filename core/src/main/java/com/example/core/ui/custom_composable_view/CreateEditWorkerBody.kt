package com.example.core.ui.custom_composable_view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.widget.TheStoreOnActionToolbar

@Preview
@Composable
fun CreateEditContentBodyPreview() {
    CreateEditContentBody(com.example.theme.R.string.worker,{}, {}) {}
}

@Composable
fun CreateEditContentBody(
    @StringRes titleResId: Int,
    pressOnBack: () -> Unit,
    editCreateClick: () -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TheStoreOnActionToolbar(
                titleResId,
                pressOnBack = pressOnBack,
                pressEditCreate = editCreateClick
            )
        },
        content = { pageContent.invoke(it) }
    )
}