package the.store.presentation.workers.create_edit_worker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.base.states.BaseViewState
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.ErrorView
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.extensions.modifiers.cast
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiEvent
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
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    CreateEditWorkerBody(
        {
            navController.popBackStack()
        },
        {
            navController.popBackStack()
        }
    ) {
        when (uiState) {
            is BaseViewState.Data -> CreateEditWorkerContent()
            is BaseViewState.Empty -> EmptyView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
//                    viewModel.onTriggerEvent(WorkersUiEvent.InitUiScreen)
                }
            )

            is BaseViewState.Loading -> LoadingView()
        }
    }

    LaunchedEffect(key1 = viewModel, block = {
//        val userPhone = bundle.getString(Constants.USER_PHONE_NUMBER, Constants.EMPTY_STRING)
//        val userPassword = bundle.getString(Constants.USER_PASSWORD, Constants.EMPTY_STRING)
        viewModel.onTriggerEvent(
            CreateEditWorkerUiEvent.InitUiContent
        )
    })

}