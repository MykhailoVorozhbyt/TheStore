package the.store.presentation.login_to_app.login

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.ui.widget.LoadingDialogView
import com.example.core.utils.AppLogger
import com.example.core.utils.helpers.showMessage
import the.store.presentation.login_to_app.login.models.LoginUiState


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.userLoggedIn -> {
            AppLogger.log("uiState.userLoggedIn")
            LaunchedEffect(uiState) {
                navController.navigate(Graph.Primary.route) {
                    popUpTo(Graph.Login.route) {
                        inclusive = true
                    }
                }
                //TODO: Fix the problem with the state later
                viewModel.setState(LoginUiState())
            }
        }
        uiState.userNotLoggedIn -> {
            AppLogger.log("uiState.userNotLoggedIn")
            LaunchedEffect(uiState) {
                navController.navigate(
                    Screen.Registration.setUserData(
                        uiState.phoneValue,
                        uiState.passwordValue,
                    )
                )
                //TODO: Fix the problem with the state later
                viewModel.setState(LoginUiState())
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
