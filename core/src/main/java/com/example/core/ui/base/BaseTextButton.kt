package com.example.core.ui.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.theme.R


@Preview
@Composable
fun BaseTextButtonPreview() {
    BaseTextButton(
        text = stringResource(id = R.string.registration),
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        textModifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun BaseTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier.fillMaxWidth()
) =
    TextButton(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults
            .buttonColors(
                containerColor = colorResource(id = R.color.white)
            ),
    ) {
        Text(
            modifier = textModifier,
            text = text,
            color = colorResource(id = R.color.app_black),
            textAlign = TextAlign.Center
        )
    }
