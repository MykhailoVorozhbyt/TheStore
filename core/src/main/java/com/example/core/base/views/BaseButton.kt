package com.example.core.base.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor

@PreviewLightDark
@Composable
fun BaseButtonPreview() {
    BaseButton(
        text = stringResource(id = R.string.registration),
        onClick = {},
        buttonModifier = Modifier.fillMaxWidth(),
        textModifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun BaseButton(
    text: String,
    buttonModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    return Button(
        modifier = buttonModifier.height(36.dp),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = TheStoreColors.blackOrWhiteColor
        ),
    ) {
        Text(
            modifier = textModifier,
            text = text,
            color = TheStoreColors.whiteOrBlackColor,
            textAlign = TextAlign.Center
        )
    }
}
