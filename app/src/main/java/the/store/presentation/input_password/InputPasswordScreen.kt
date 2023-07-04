package the.store.presentation.input_password

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
fun InputPasswordScreen(navController: NavHostController, userNumber: String) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Greeting("InputPasswordScreen $userNumber", navController = navController)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello $name!", modifier = modifier
        )
        Button(onClick = {
            navController.navigate(Screen.AvailableCashDesks.route)
        }) {
            Text(
                text = "To AvailableCashDesksScreen"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheStoreTheme {
        val navGraph = rememberNavController()
        Greeting("InputPasswordScreen", navController = navGraph)
    }
}
