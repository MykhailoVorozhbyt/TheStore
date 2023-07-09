package the.store.ui.custom_view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
        titleText = "title text",
        onValueChange = {

        },
        hintText = "hint text"
    )
}

@Composable
fun InputTextField(
    titleText: String? = null,
    onValueChange: (String) -> Unit,
    hintText: String = stringResource(id = R.string.input_text),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = 1
) {
    var fieldText by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
        TextField(
            value = fieldText,
            modifier = Modifier
                .fillMaxWidth(),
            shape = baseRoundedCornerShape(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(R.color.white),
                focusedIndicatorColor = colorResource(R.color.transparentColor),
                unfocusedIndicatorColor = colorResource(R.color.transparentColor),
            ),
            onValueChange = { text ->
                fieldText = text
                onValueChange(fieldText.text)
            },
            placeholder = {
                Text(text = hintText)
            },
            keyboardOptions = keyboardOptions,
            maxLines = maxLines
        )
    }
}