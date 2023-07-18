package the.store.ui.custom_view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.utils.elvis
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultTextBottomPadding
import com.example.theme.R


@Preview
@Composable
fun CustomTextFieldPreview() {
    InputTextField(
        titleText = "title text", onValueChange = {

        }, hintText = "hint text"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    titleText: String? = null,
    onValueChange: (String) -> Unit,
    hintText: String = stringResource(id = R.string.input_text),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    isError: Boolean = false,
    errorMessage: String = stringResource(id = R.string.error)
) {
    var fieldText by remember { mutableStateOf(TextFieldValue("")) }
    var isErrorState by rememberSaveable { mutableStateOf(isError) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (titleText.isNullOrBlank().not()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultTextBottomPadding(),
                text = titleText.elvis(),
                color = colorResource(id = R.color.white)
            )
        }
        TextField(value = fieldText,
            modifier = Modifier.fillMaxWidth(),
            shape = baseRoundedCornerShape(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(R.color.white),
                focusedIndicatorColor = colorResource(R.color.transparentColor),
                unfocusedIndicatorColor = colorResource(R.color.transparentColor),
                errorIndicatorColor = colorResource(R.color.transparentColor),
            ),
            onValueChange = { text ->
                isErrorState = false
                fieldText = text
                onValueChange(fieldText.text)
            },
            singleLine = singleLine,
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
            })
    }
}