package the.store.presentation.login_to_app.login

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.ui.widget.LoadingDialogView
import com.example.core.utils.AppLogger
import com.example.core.utils.helpers.showMessage
import the.store.presentation.login_to_app.login.models.LoginUiEvent


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    when {
        uiState.userLoggedIn -> {
            AppLogger.log("uiState.userLoggedIn")
            DisposableEffect(lifecycleOwner) {
                navController.navigate(Graph.PrimaryScreen.route) {
                    popUpTo(Graph.Login.route) {
                        inclusive = true
                    }
                }
                onDispose {
                    viewModel.onTriggerEvent(LoginUiEvent.DeleteAllStateExceptData)
                }
            }
        }
        uiState.userNotLoggedIn -> {
            AppLogger.log("uiState.userNotLoggedIn")
            DisposableEffect(key1 = viewModel) {
                navController.navigate(
                    Screen.Registration.setUserData(
                        uiState.phoneValue,
                        uiState.passwordValue,
                    )
                )
                onDispose {
                    viewModel.onTriggerEvent(LoginUiEvent.DeleteAllStateExceptData)
                }
            }
        }
        uiState.isLoading -> {
            AppLogger.log("uiState.isLoading")
            LoadingDialogView(true)
        }
        else -> {
            LoginContent(
                uiState,
                viewModel
            )
        }
    }
    if (uiState.errorState.errorMessage.isNullOrBlank().not()) {
        AppLogger.log("uiState.isLoading")
        showMessage(context, uiState.errorState.errorMessage)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navGraph = rememberNavController()
    LoginScreen(navGraph)
}
