package the.store.presentation.login_to_app.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.base.states.BaseViewState
import com.example.core.ui.widget.ProgressIndicator
import com.example.core.utils.extensions.modifiers.cast
import com.example.core.utils.extensions.modifiers.loginIconSize
import com.example.theme.R
import the.store.presentation.login_to_app.login.utils.LoginUiEvent
import the.store.presentation.login_to_app.login.utils.LoginUiState


@Composable
fun LoginScreen(
    navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

//    val uiState by remember {
//        viewModel.uiState
//    }

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
        when (uiState) {
            is BaseViewState.Loading -> {
                ProgressIndicator(
                    Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is BaseViewState.Data -> {
                InputDataContainerContent(
                    data = uiState.cast<BaseViewState.Data<LoginUiState>>().value,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            is BaseViewState.Error -> {

            }

//            else -> {
//                InputDataContainerContent(
//                    data = uiState.cast<BaseViewState.Data<LoginUiState>>().value,
//                    viewModel = viewModel,
//                    navController = navController
//                )
//            }
            else -> {

            }
        }
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(LoginUiEvent.InitView)
    })

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navGraph = rememberNavController()
    LoginScreen(navGraph)
}
