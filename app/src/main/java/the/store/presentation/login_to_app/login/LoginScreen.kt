package the.store.presentation.login_to_app.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    navController: NavHostController,
    viewModel: LoginScreenViewModel  = hiltViewModel()
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
        InputDataView(navController = navController)
    }
}

@Composable
fun InputDataView(navController: NavHostController) {
    var phoneErrorState by rememberSaveable { mutableStateOf(false) }
    var passwordErrorState by rememberSaveable { mutableStateOf(false) }

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
            stringResource(
                id = R.string.input_phone
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { resultText ->
            }, isError = phoneErrorState,
            errorMessage = stringResource(id = R.string.error)
        )
        BaseSpacer()
        InputTextField(
            stringResource(id = R.string.input_password), { tesultText ->
            }, isError = passwordErrorState,
            errorMessage = stringResource(id = R.string.error)
        )
        BaseSpacer()
        BaseSpacerColorView(colorResource(id = R.color.white))
        BaseSpacer()
        BaseButton(
            text = stringResource(id = R.string.login),
            onClick = {
                navController.navigate(
                    Screen.InputPassword.route
                )
            },
            textModifier = Modifier.fillMaxWidth(),
        )
        BaseSpacer()
        BaseButton(
            text = stringResource(id = R.string.registration),
            onClick = {
                navController.navigate(
                    Screen.Registration.route
                )
            },
            textModifier = Modifier.fillMaxWidth(),
        )
    }
}

fun checkValidData(
    phone: String,
    password: String
) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navGraph = rememberNavController()
    LoginScreen(navGraph)
}
