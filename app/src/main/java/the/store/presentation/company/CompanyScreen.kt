package the.store.presentation.company

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.core.base.states.BaseViewState
import com.example.core.ui.custom_composable_view.CreateEditContentBody
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.ErrorView
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.extensions.modifiers.cast
import com.example.core.utils.helpers.showMessage
import the.store.presentation.company.models.CompanyUiState
import the.store.presentation.company.views.CompanyContent

@Composable
fun CompanyScreen(
    uiState: BaseViewState<*>,
    initUiContent: () -> Unit,
    editClick: () -> Unit,
    pressOnBack: () -> Unit,
    photoChanged: (Uri) -> Unit,
    deletePhoto: () -> Unit,
    nameChanged: (String) -> Unit,
    descriptionChanged: (String) -> Unit,
) {
    val context = LocalContext.current

    CreateEditContentBody(
        titleResId = com.example.theme.R.string.company,
        pressOnBack = pressOnBack,
        editCreateClick = editClick
    ) {
        when (uiState) {
            is BaseViewState.Data -> {
                val state = uiState.cast<BaseViewState.Data<CompanyUiState>>().value
                if (state.companyUpdate) {
                    DisposableEffect(uiState) {
                        showMessage(
                            context,
                            com.example.theme.R.string.company_data_updated_successfully
                        )
                        pressOnBack.invoke()
                        onDispose {}
                    }
                }
                CompanyContent(
                    state,
                    photoChanged = photoChanged,
                    deletePhoto = deletePhoto,
                    nameChanged = nameChanged,
                    descriptionChanged = descriptionChanged
                )
            }

            is BaseViewState.Empty -> EmptyView()
            is BaseViewState.Loading -> LoadingView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    pressOnBack.invoke()
                }
            )
        }
    }
    LaunchedEffect(true) {
        initUiContent.invoke()
    }
}