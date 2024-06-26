package the.store.presentation.workers.create_edit_worker

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.base.states.BaseViewState
import com.example.core.ui.custom_composable_view.CreateEditContentBody
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.ErrorView
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.extensions.modifiers.cast
import com.example.core.utils.helpers.showMessage
import com.example.theme.R
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiEvent
import the.store.presentation.workers.create_edit_worker.models.CreateEditWorkerUiState
import the.store.presentation.workers.create_edit_worker.views.CreateEditWorkerContent

@PreviewLightDark
@Composable
fun CreateEditWorkerScreenPreview() {
    CreateEditWorkerScreen(navController = rememberNavController(), workerId = 1)
}

@Composable
fun CreateEditWorkerScreen(
    viewModel: CreateEditWorkerViewModel = hiltViewModel(),
    navController: NavHostController,
    workerId: Long
) {
    val uiState by viewModel.uiState.collectAsState()
    val context: Context = LocalContext.current

    CreateEditContentBody(
        com.example.theme.R.string.worker,
        pressOnBack = { navController.popBackStack() },
        editCreateClick = {
            viewModel.onTriggerEvent(CreateEditWorkerUiEvent.SubmitCreateEditClick)
        }
    ) {
        when (uiState) {
            is BaseViewState.Data -> {
                val currentState = uiState.cast<BaseViewState.Data<CreateEditWorkerUiState>>().value
                if (currentState.actionProduct) {
                    DisposableEffect(uiState) {
                        navController.popBackStack()
                        when (currentState.id) {
                            0L -> {
                                showMessage(context, R.string.employee_created_successfully)
                            }

                            else -> {
                                showMessage(
                                    context, R.string.employee_data_updated_successfully
                                )
                            }
                        }
                        onDispose {}
                    }
                }
                if (currentState.deleteProduct) {
                    DisposableEffect(uiState) {
                        navController.popBackStack()
                        showMessage(context, R.string.employer_deleted_successfully)
                        onDispose {}
                    }
                }
                CreateEditWorkerContent(
                    currentState,
                    workerPhotoUri = { uri ->
                        viewModel.onTriggerEvent(CreateEditWorkerUiEvent.PhotoChanged(uri))
                    },
                    deletePhotoUri = {
                        viewModel.onTriggerEvent(CreateEditWorkerUiEvent.DeletePhotoUri)
                    },
                    workerName = { name ->
                        viewModel.onTriggerEvent(CreateEditWorkerUiEvent.NameChanged(name))
                    },
                    workerSurname = { surname ->
                        viewModel.onTriggerEvent(CreateEditWorkerUiEvent.SurnameChanged(surname))
                    },
                    workerPassword = { password ->
                        viewModel.onTriggerEvent(CreateEditWorkerUiEvent.PasswordChanged(password))
                    },
                    workerPhone = { phone ->
                        viewModel.onTriggerEvent(CreateEditWorkerUiEvent.PhoneChanged(phone))
                    },
                    workerEmailAddress = { emailAddress ->
                        viewModel.onTriggerEvent(
                            CreateEditWorkerUiEvent.EmailAddressChanged(
                                emailAddress
                            )
                        )
                    },
                    deleteEmployer = { id ->
                        viewModel.onTriggerEvent(CreateEditWorkerUiEvent.DeleteEmployerClick(id))
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