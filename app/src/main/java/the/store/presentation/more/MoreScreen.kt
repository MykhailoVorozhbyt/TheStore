package the.store.presentation.more

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.LoadingView
import com.example.core.ui.widget.TheStoreToolbar
import com.example.theme.R
import the.store.presentation.main.ComposableHelloText

@Composable
fun MoreScreen(
    viewModel: MoreScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    MoreScreenBody(
        pressOnBack = { /*navController.navigateUp()*/ }
    ) {
        when {
            uiState.isLoading -> {
                LoadingView()
            }
            uiState.screenUi.isNotEmpty() -> {

            }

            else -> {
                EmptyView()
            }

        }
        ComposableHelloText("MoreScreen")

    }
}

@Composable
fun MoreScreenBody(
    pressOnBack: () -> Unit = {},
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TheStoreToolbar(
                R.string.more,
                pressOnBack = pressOnBack
            )
        },
        content = { pageContent.invoke(it) }
    )
}