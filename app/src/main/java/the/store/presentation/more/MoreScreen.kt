package the.store.presentation.more

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import the.store.presentation.main.ComposableHelloText

@Composable
fun MoreScreen(
    viewModel: MoreScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ComposableHelloText("MoreScreen")
    }
}