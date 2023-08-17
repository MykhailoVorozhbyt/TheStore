package com.example.core.ui.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.theme.R
import com.example.theme.White

@Preview
@Composable
fun BaseButtonPreview() {
    BaseButton(
        text = stringResource(id = R.string.registration),
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        textModifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun BaseButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier
) {
    return Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults
            .buttonColors(containerColor = White),
    ) {
        Text(
            modifier = textModifier.fillMaxWidth(),
            text = text,
            color = colorResource(id = R.color.app_black),
            textAlign = TextAlign.Center
        )
    }
}
