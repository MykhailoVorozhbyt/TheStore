package the.store.presentation.login_to_app.registration

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.navigation.Graph
import com.example.core.utils.extensions.modifiers.defaultPadding

@Composable
fun RegistrationScreen(
    navController: NavHostController,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .defaultPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Button(
            onClick = {
                navController.navigate(Graph.Primary.route) {
                    popUpTo(Graph.Login.route)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "RegistrationScreen")
        }
    }
}