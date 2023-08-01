package the.store.presentation.login_to_app.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.ui.base.BaseButton
import com.example.core.ui.base.BaseSpacer
import com.example.core.ui.base.BaseSpacerColorView
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.ui.widget.ProgressIndicator
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultPadding
import com.example.core.utils.extensions.modifiers.loginIconSize
import com.example.core.utils.helpers.showMessage
import com.example.theme.R


@Composable
fun LoginScreen(
    navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val localContext = LocalContext.current

    var userNotLoggedInState by remember { mutableStateOf(0) }
    var userLoggedInState by remember { mutableStateOf(0) }

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
                userLoggedInState++
                showMessage(localContext, "userLoggedIn: $userLoggedInState")

                LaunchedEffect(true) {
                    navController.navigate(Graph.Primary.route) {
                        popUpTo(Graph.Login.route) {
                            inclusive = true
                        }
                    }
                }
            }
            uiState.userNotLoggedIn -> {
                userNotLoggedInState++
                showMessage(localContext, "userNotLoggedInState: $userNotLoggedInState")

                LaunchedEffect(true) {
                    navController.navigate(
                        Screen.Registration.route
                    )
                }
            }
            else -> {
                InputDataContainerView(
                    uiState = uiState,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun InputDataContainerView(uiState: LoginState, viewModel: LoginViewModel) {
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
        if (uiState.isLoading) {
            ProgressIndicator(
                Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LoginInputsView(uiState, viewModel)
        }
    }
}

@Composable
fun LoginInputsView(
    uiState: LoginState, viewModel: LoginViewModel
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navGraph = rememberNavController()
    LoginScreen(navGraph)
}
