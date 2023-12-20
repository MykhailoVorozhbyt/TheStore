package the.store.presentation.workers.create_edit_worker

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.base.states.BaseViewState
import com.example.core.domain.constants.Constants
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.ErrorView
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.extensions.modifiers.cast
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiEvent
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiState
import the.store.presentation.workers.create_edit_worker.views.CreateEditWorkerBody
import the.store.presentation.workers.create_edit_worker.views.CreateEditWorkerContent

@Preview
@Composable
fun CreateEditWorkerScreenPreview() {
    CreateEditWorkerScreen(navController = rememberNavController())
}

@Composable
fun CreateEditWorkerScreen(
    viewModel: CreateEditWorkerViewModel = hiltViewModel(),
    navController: NavHostController,
    bundle: Bundle = bundleOf()
) {
    val uiState by viewModel.uiState.collectAsState()
    val workerId = bundle.getLong(Constants.WORKER_ID, 0L)

    CreateEditWorkerBody(
        {
            navController.popBackStack()
        },
        {
            viewModel.createOrUpdateWorker()
        }
    ) {
        when (uiState) {
            is BaseViewState.Data -> {
                val currentState = uiState.cast<BaseViewState.Data<CreateEditWorkerUiState>>().value
                CreateEditWorkerContent(
                    currentState,
                    { name ->

                    }, { surname ->

                    }, { password ->

                    }, { phone ->

                    }, { emailAddress ->

                    }
                )
            }

            is BaseViewState.Empty -> EmptyView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    viewModel.onTriggerEvent(
                        CreateEditWorkerUiEvent.InitUiContent(workerId)
                    )
                }
            )

            is BaseViewState.Loading -> LoadingView()
        }
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(
            CreateEditWorkerUiEvent.InitUiContent(workerId)
        )
    })

}