package the.store.presentation.login_to_app.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.ui.base.BaseButton
import com.example.core.ui.base.BaseSpacer
import com.example.core.ui.base.BaseSpacerColorView
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.utils.AppLogger
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.theme.R
import the.store.presentation.login_to_app.login.utils.LoginUiEvent
import the.store.presentation.login_to_app.login.utils.LoginUiState


@Composable
fun InputDataContainerContent(
    data: LoginUiState,
    viewModel: LoginViewModel,
    navController: NavHostController
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
        when {
            data.userLoggedIn -> {
                AppLogger.log("userLoggedIn")
                LaunchedEffect(data) {
                    navController.navigate(Graph.Primary.route) {
                        popUpTo(Graph.Login.route) {
                            inclusive = true
                        }
                    }
                }
            }
            data.userNotLoggedIn -> {
                AppLogger.log("userNotLoggedIn")
                LaunchedEffect(data) {
                    navController.navigate(
                        Screen.Registration.route
                    )
                }
            }
            else -> {
                LoginInputsView(data, viewModel)
            }
        }
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
    BaseSpacer()
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
    BaseSpacer()
    BaseSpacerColorView(colorResource(id = R.color.white))
    BaseSpacer()
    BaseButton(
        text = stringResource(id = R.string.login),
        onClick = {
            viewModel.onTriggerEvent(LoginUiEvent.SubmitLoginClick)
        }
    )
    BaseSpacer()
    BaseSpacerColorView(colorResource(id = R.color.white))
    BaseSpacer()
}