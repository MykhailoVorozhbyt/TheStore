package the.store.presentation.workers

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.base.states.BaseViewState
import com.example.core.navigation.Screen
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.ErrorView
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.extensions.modifiers.cast
import the.store.presentation.workers.models.WorkersUiEvent
import the.store.presentation.workers.models.WorkersUiState
import the.store.presentation.workers.views.WorkersScreenBody
import the.store.presentation.workers.views.WorkersScreenContent

@Composable
fun WorkersScreen(
    viewModel: WorkersViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    WorkersScreenBody(
        {
            navController.navigate(Screen.Worker.setUserData(0L))
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            when (uiState) {
                is BaseViewState.Data -> {
                    val currentState = uiState.cast<BaseViewState.Data<WorkersUiState>>().value
                    WorkersScreenContent(
                        currentState,
                        { text ->
                            viewModel.onTriggerEvent(
                                WorkersUiEvent.InputValueChanged(
                                    text
                                )
                            )
                        }, { workerId ->
                            navController.navigate(Screen.Worker.setUserData(workerId))
                        }
                    )
                }

                is BaseViewState.Empty -> EmptyView()
                is BaseViewState.Loading -> LoadingView()
                is BaseViewState.Error -> ErrorView(
                    e = uiState.cast<BaseViewState.Error>().throwable,
                    action = {
                        viewModel.onTriggerEvent(WorkersUiEvent.InitUiScreen)
                    }
                )
            }
        }
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.onTriggerEvent(WorkersUiEvent.InitUiScreen)
    }

}