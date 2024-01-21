package com.example.core.ui.custom_composable_view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.utils.elvis
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultTextBottomPadding
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor


@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview
@Composable
fun CustomTextFieldPreview() {
    InputTextField(
        onValueChange = {

        },
        titleText = "title text",
        textValue = "041244",
        hintText = "hint text"
    )
}

@Composable
fun InputTextField(
    onValueChange: (String) -> Unit,
    textValue: String,
    titleText: String? = null,
    hintText: String = stringResource(id = R.string.input_text),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    isError: Boolean = false,
    errorMessage: String = stringResource(id = R.string.error),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    columnModifier: Modifier = Modifier
) {
    var fieldText by remember { mutableStateOf(textValue) }
    var isErrorState by rememberSaveable { mutableStateOf(isError) }

    Column(
        modifier = columnModifier.fillMaxWidth()
    ) {
        if (titleText.isNullOrBlank().not()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultTextBottomPadding(),
                text = titleText.elvis(),
                color = TheStoreColors.whiteOrBlackColor
            )
        }
        val containerColor = TheStoreColors.whiteOrBlackColor
        TextField(
            value = fieldText,
            modifier = Modifier.fillMaxWidth(),
            shape = baseRoundedCornerShape(),
            textStyle = TextStyle(
                color = TheStoreColors.blackOrWhiteColor
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = colorResource(R.color.transparentColor),
                unfocusedIndicatorColor = colorResource(R.color.transparentColor),
                errorIndicatorColor = colorResource(R.color.transparentColor),
                cursorColor = TheStoreColors.blackOrWhiteColor,
                focusedTextColor = TheStoreColors.blackOrWhiteColor,
            ),
            onValueChange = { text ->
                isErrorState = false
                fieldText = text
                onValueChange(fieldText)
            },
            singleLine = singleLine,
            enabled = enabled,
            readOnly = readOnly,
            placeholder = {
                Text(
                    text = hintText, color = colorResource(
                        id = R.color.hintColor
                    )
                )
            },
            keyboardOptions = keyboardOptions,
            maxLines = maxLines,
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        text = errorMessage, color = colorResource(
                            id = R.color.errorColor
                        )
                    )
                }
            },
            trailingIcon = {
                if (isError) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_error), tint = colorResource(
                            id = R.color.errorColor
                        ), contentDescription = "Varning Icon"
                    )
                }
            }
        )
    }
}