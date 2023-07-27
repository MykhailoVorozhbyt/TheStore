package the.store.presentation.login_to_app.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.Screen
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.core.utils.extensions.modifiers.loginIconSize
import com.example.theme.R
import the.store.ui.base.BaseButton
import the.store.ui.base.BaseSpacer
import the.store.ui.base.BaseSpacerColorView
import the.store.ui.custom_view.InputTextField


@Composable
fun LoginScreen(
    navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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

        when {
            uiState.userLoggedIn -> {
                LaunchedEffect(key1 = true) {
                    navController.navigate(
                        Screen.AvailableCashDesks.route
                    )
                }
            }
            uiState.userNotLoggedIn -> {
                LaunchedEffect(key1 = true) {
                    navController.navigate(
                        Screen.Registration.route
                    )
                }
            }
            else -> {
                InputDataView(
                    uiState = uiState,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun InputDataView(
    uiState: LoginState, viewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .defaultPadding()
            .background(
                colorResource(
                    id = R.color.app_black
                ), shape = baseRoundedCornerShape()
            )
            .defaultPadding()
    ) {
        InputTextField(
            { resultText ->
                viewModel.onTriggerEvent(
                    LoginUiEvent.PhoneChanged(
                        resultText
                    )
                )
            },
            textValue = uiState.phoneValue,
            titleText = stringResource(
                id = R.string.input_phone
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            errorMessage = stringResource(id = uiState.inputDataErrorState.phoneErrorState.errorMessageStringResource),
            isError = uiState.inputDataErrorState.phoneErrorState.hasError,
        )
        BaseSpacer()
        InputTextField(
            { resultText ->
                viewModel.onTriggerEvent(
                    LoginUiEvent.PasswordChanged(
                        resultText
                    )
                )
            },
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
            },
            textModifier = Modifier.fillMaxWidth(),
        )
        BaseSpacer()
        BaseSpacerColorView(colorResource(id = R.color.white))
        BaseSpacer()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navGraph = rememberNavController()
    LoginScreen(navGraph)
}
