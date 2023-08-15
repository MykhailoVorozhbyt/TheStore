package the.store.presentation.login_to_app.registration

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.domain.constants.Constants
import com.example.core.navigation.Graph
import com.example.core.utils.extensions.modifiers.defaultPadding

@Composable
fun RegistrationScreen(
    navController: NavHostController,
    viewModel: RegistrationViewModel = hiltViewModel(),
    bundle: Bundle?
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .defaultPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
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

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Phone: ${
                    bundle?.getString(
                        Constants.USER_PHONE_NUMBER,
                        Constants.EMPTY_STRING
                    )
                }"
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Password: ${
                    bundle?.getString(
                        Constants.USER_PASSWORD,
                        Constants.EMPTY_STRING
                    )
                }"
            )
        }

    }
}