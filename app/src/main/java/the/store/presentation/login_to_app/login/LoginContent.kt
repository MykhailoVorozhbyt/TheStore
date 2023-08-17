package the.store.presentation.login_to_app.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.core.ui.base.BaseButton
import com.example.core.ui.base.SmallSpacer
import com.example.core.ui.base.SpacerColorView
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.core.utils.extensions.modifiers.loginIconSize
import com.example.theme.R
import the.store.presentation.login_to_app.login.models.LoginUiEvent
import the.store.presentation.login_to_app.login.models.LoginUiState

@Composable
fun LoginContent(
    uiState: LoginUiState,
    viewModel: LoginViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.loginIconSize(),
            painter = painterResource(id = R.drawable.ic_the_store),
            contentDescription = "Logo icon",
        )
        InputDataContainerContent(
            data = uiState,
            viewModel = viewModel
        )
    }
}


@Composable
fun InputDataContainerContent(
    data: LoginUiState,
    viewModel: LoginViewModel
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
            .defaultPadding()
    ) {
        LoginInputsView(data, viewModel)
    }
}

@Composable
fun LoginInputsView(
    uiState: LoginUiState, viewModel: LoginViewModel
) {
    InputTextField(
        onValueChange = { resultText ->
            viewModel.onTriggerEvent(
                LoginUiEvent.PhoneChanged(
                    resultText
                )
            )
        },
        textValue = uiState.phoneValue,
        titleText = stringResource(id = R.string.input_phone),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        errorMessage = stringResource(id = uiState.inputDataErrorState.phoneErrorState.errorMessageStringResource),
        isError = uiState.inputDataErrorState.phoneErrorState.hasError,
    )
    SmallSpacer()
    InputTextField(
        onValueChange = { resultText ->
            viewModel.onTriggerEvent(
                LoginUiEvent.PasswordChanged(
                    resultText
                )
            )
        },
        textValue = uiState.passwordValue,
        titleText = stringResource(id = R.string.input_password),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        errorMessage = stringResource(id = uiState.inputDataErrorState.passwordErrorState.errorMessageStringResource),
        isError = uiState.inputDataErrorState.passwordErrorState.hasError
    )
    SmallSpacer()
    SpacerColorView(colorResource(id = R.color.white))
    SmallSpacer()
    BaseButton(
        text = stringResource(id = R.string.login),
        onClick = {
            viewModel.onTriggerEvent(LoginUiEvent.SubmitLoginClick)
        }
    )
    SmallSpacer()
    SpacerColorView(colorResource(id = R.color.white))
    SmallSpacer()
}