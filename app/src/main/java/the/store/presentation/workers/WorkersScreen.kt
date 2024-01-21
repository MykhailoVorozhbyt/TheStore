package the.store.presentation.workers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.base.states.BaseViewState
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.ErrorView
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.extensions.modifiers.cast
import the.store.presentation.workers.models.WorkersUiState
import the.store.presentation.workers.views.AddTopAppBar
import the.store.presentation.workers.views.WorkersScreenContent

@Composable
fun WorkersScreen(
    uiState: BaseViewState<*>,
    initUi: () -> Unit,
    createWorker: () -> Unit,
    searchText: (String) -> Unit,
    workerClick: (Long) -> Unit,
    refreshAction: () -> Unit,
) {
    AddTopAppBar(stringResource(com.example.theme.R.string.workers), { createWorker.invoke() }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            when (uiState) {
                is BaseViewState.Data -> {
                    val currentState = uiState.cast<BaseViewState.Data<WorkersUiState>>().value
                    WorkersScreenContent(
                        uiState = currentState,
                        searchText = { searchText.invoke(it) },
                        workerClick = { workerClick.invoke(it) },
                        refreshAction = { refreshAction.invoke() },
                    )
                }

                is BaseViewState.Empty -> EmptyView()
                is BaseViewState.Loading -> LoadingView()
                is BaseViewState.Error -> ErrorView(
                    e = uiState.cast<BaseViewState.Error>().throwable,
                    action = {
                        initUi.invoke()
                    }
                )
            }
        }
    }
    LaunchedEffect(key1 = true) {
        initUi.invoke()
    }

}