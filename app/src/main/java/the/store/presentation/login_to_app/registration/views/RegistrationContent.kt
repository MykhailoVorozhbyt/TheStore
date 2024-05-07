package the.store.presentation.login_to_app.registration.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.core.base.views.BaseButton
import com.example.core.base.views.HorizontalSpacerColorView
import com.example.core.base.views.SmallSpacer
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.theme.R
import the.store.presentation.login_to_app.registration.models.RegistrationUiState

@PreviewLightDark
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
            verticalArrangement = Arrangement.Center
        ) {
            SmallSpacer()
            InputTextField(
                onValueChange = { resultText ->
                    nameChanged.invoke(resultText)
                },
                textValue = data.name,
                titleText = stringResource(id = R.string.input_name),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                errorMessage = stringResource(id = data.inputDataErrorState.nameErrorState.errorStringRes),
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
                errorMessage = stringResource(id = data.inputDataErrorState.surnameErrorState.errorStringRes),
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
                errorMessage = stringResource(id = data.inputDataErrorState.phoneErrorState.errorStringRes),
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
                errorMessage = stringResource(id = data.inputDataErrorState.phoneErrorState.errorStringRes),
                isError = data.inputDataErrorState.phoneErrorState.hasError,
            )
            SmallSpacer()
            HorizontalSpacerColorView(colorResource(id = R.color.white))
            SmallSpacer()
            BaseButton(
                text = stringResource(id = R.string.register),
                onClick = {
                    registerClick.invoke()
                },
                buttonModifier = Modifier.fillMaxWidth()
            )
            SmallSpacer()
            HorizontalSpacerColorView(colorResource(id = R.color.white))
            SmallSpacer()
        }
    }
}