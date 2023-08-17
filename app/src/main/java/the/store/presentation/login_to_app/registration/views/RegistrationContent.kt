package the.store.presentation.login_to_app.registration.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.base.BaseButton
import com.example.core.ui.base.SmallSpacer
import com.example.core.ui.base.SpacerColorView
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.theme.R
import the.store.presentation.login_to_app.registration.models.RegistrationUiState

@Preview
@Composable
fun RegistrationContentPreview() {
    RegistrationContent(data = RegistrationUiState(),
        nameChanged = {},
        surnameChanged = {},
        phoneChanged = {},
        passwordChanged = {},
        registerClick = {

        })
}

@Composable
fun RegistrationContent(
    data: RegistrationUiState,
    nameChanged: (String) -> Unit,
    surnameChanged: (String) -> Unit,
    phoneChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
    registerClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .defaultPadding()
                .background(
                    colorResource(
                        id = R.color.app_black
                    ), shape = baseRoundedCornerShape()
                )
                .defaultPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SmallSpacer()
            InputTextField(
                onValueChange = { resultText ->
                    nameChanged.invoke(resultText)
                },
                textValue = data.name,
                titleText = stringResource(id = R.string.input_name),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                errorMessage = stringResource(id = data.inputDataErrorState.nameErrorState.errorMessageStringResource),
                isError = data.inputDataErrorState.nameErrorState.hasError,
            )
            SmallSpacer()
            InputTextField(
                onValueChange = { resultText ->
                    surnameChanged.invoke(resultText)
                },
                textValue = data.surname,
                titleText = stringResource(id = R.string.input_surname),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                errorMessage = stringResource(id = data.inputDataErrorState.surnameErrorState.errorMessageStringResource),
                isError = data.inputDataErrorState.surnameErrorState.hasError,
            )
            SmallSpacer()
            InputTextField(
                onValueChange = { resultText ->
                    phoneChanged.invoke(resultText)
                },
                textValue = data.phone,
                titleText = stringResource(id = R.string.input_phone),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                errorMessage = stringResource(id = data.inputDataErrorState.phoneErrorState.errorMessageStringResource),
                isError = data.inputDataErrorState.phoneErrorState.hasError,
            )
            SmallSpacer()
            InputTextField(
                onValueChange = { resultText ->
                    passwordChanged.invoke(resultText)
                },
                textValue = data.password,
                titleText = stringResource(id = R.string.input_phone),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                errorMessage = stringResource(id = data.inputDataErrorState.phoneErrorState.errorMessageStringResource),
                isError = data.inputDataErrorState.phoneErrorState.hasError,
            )
            SmallSpacer()
            SpacerColorView(colorResource(id = R.color.white))
            SmallSpacer()
            BaseButton(
                text = stringResource(id = R.string.register),
                onClick = {
                    registerClick.invoke()
                }
            )
            SmallSpacer()
            SpacerColorView(colorResource(id = R.color.white))
            SmallSpacer()
        }

    }
}