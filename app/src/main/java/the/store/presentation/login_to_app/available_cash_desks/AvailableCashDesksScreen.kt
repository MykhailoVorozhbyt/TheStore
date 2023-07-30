package the.store.presentation.login_to_app.available_cash_desks

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
import com.example.core.navigation.Graph
import com.example.theme.TheStoreTheme


@Composable
fun AvailableCashDesksScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Greeting("AvailableCashDesksScreen", navController = navController)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello $name!", modifier = modifier
        )
        Button(onClick = {
            navController.navigate(Graph.Primary.route) {
                popUpTo(Graph.Login.route)
            }
        }) {
            Text(
                text = "To MainScreen (Graph)"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheStoreTheme {
        val navGraph = rememberNavController()
        Greeting("AvailableCashDesksScreen", navController = navGraph)
    }
}
