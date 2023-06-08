package the.store.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.Screen
import the.store.ui.theme.TheStoreTheme


@Composable
fun LoginScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Greeting("LoginScreen", navController = navController)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello $name!", modifier = modifier
        )
        Button(
            onClick = {
                navController.navigate(Screen.InputPassword.route)
            }) {
            Text(
                text = "To InputPasswordScreen"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navGraph = rememberNavController()
    TheStoreTheme {
        Greeting("LoginScreen", navController = navGraph)
    }
}
