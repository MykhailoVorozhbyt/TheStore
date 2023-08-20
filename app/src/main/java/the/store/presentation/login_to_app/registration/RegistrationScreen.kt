package the.store.presentation.login_to_app.registration

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.base.states.BaseViewState
import com.example.core.domain.constants.Constants
import com.example.core.navigation.Graph
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.ErrorView
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.extensions.modifiers.cast
import the.store.presentation.login_to_app.registration.models.RegistrationUiEvent
import the.store.presentation.login_to_app.registration.models.RegistrationUiState
import the.store.presentation.login_to_app.registration.views.RegistrationBody
import the.store.presentation.login_to_app.registration.views.RegistrationContent

@Composable
fun RegistrationScreen(
    navController: NavHostController,
    viewModel: RegistrationViewModel = hiltViewModel(),
    bundle: Bundle = bundleOf()
) {
    val uiState by viewModel.uiState.collectAsState()

    RegistrationBody(
        pressOnBack = { navController.navigateUp() }
    ) {
        when (uiState) {
            is BaseViewState.Data -> {
                val newState = uiState.cast<BaseViewState.Data<RegistrationUiState>>().value
                if (newState.isRegister) {
                    DisposableEffect(uiState) {
                        navController.navigate(Graph.Primary.route) {
                            popUpTo(Graph.Login.route)
                        }
                        onDispose {}
                    }
                }
                RegistrationContent(
                    newState,
                    nameChanged = {
                        viewModel.onTriggerEvent(RegistrationUiEvent.NameChanged(it))
                    },
                    surnameChanged = {
                        viewModel.onTriggerEvent(RegistrationUiEvent.SurnameChanged(it))
                    },
                    phoneChanged = {
                        viewModel.onTriggerEvent(RegistrationUiEvent.PhoneChanged(it))
                    },
                    passwordChanged = {
                        viewModel.onTriggerEvent(RegistrationUiEvent.PasswordChanged(it))
                    },
                    registerClick = {
                        viewModel.onTriggerEvent(RegistrationUiEvent.SubmitRegistrationClick)
                    }
                )
            }
            is BaseViewState.Loading -> LoadingView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    viewModel.onTriggerEvent(RegistrationUiEvent.SubmitRegistrationClick)
                }
            )
            is BaseViewState.Empty -> EmptyView()
        }
    }
    LaunchedEffect(key1 = viewModel, block = {
        val userPhone = bundle.getString(Constants.USER_PHONE_NUMBER, Constants.EMPTY_STRING)
        val userPassword = bundle.getString(Constants.USER_PASSWORD, Constants.EMPTY_STRING)
        viewModel.onTriggerEvent(
            RegistrationUiEvent.InitUiContent(
                phone = userPhone,
                password = userPassword
            )
        )
    })
}


@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        navController = rememberNavController()
    )
}