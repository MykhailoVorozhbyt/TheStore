package the.store.presentation.more

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.ui.widget.LoadingView
import com.example.core.ui.widget.TheStoreToolbar
import com.example.core.utils.enums.MoreScreenClickAction
import com.example.core.utils.helpers.showMessage
import com.example.theme.R
import the.store.presentation.more.models.MoreScreenUiEvent

@Composable
fun MoreScreen(
    viewModel: MoreScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    MoreScreenBody(
        pressOnBack = { /*navController.navigateUp()*/ }
    ) {
        when {
            uiState.isLoading -> {
                LoadingView()
            }

            uiState.screenUi.isNotEmpty() -> {
                MoreScreenContent(uiState.screenUi) {
                    moreScreenItemClick(context, it, navController)
                }
            }
        }
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(
            MoreScreenUiEvent.InitUiContent
        )
    })
}


private fun moreScreenItemClick(
    context: Context,
    clickAction: MoreScreenClickAction,
    navController: NavHostController
) {
    when (clickAction) {
        MoreScreenClickAction.UserProfileClick -> {
            showMessage(context, clickAction.name)
        }

        MoreScreenClickAction.CopyTheDeviceIdClick -> {
            showMessage(context, clickAction.name)
        }

        MoreScreenClickAction.ExitClick -> {
            showMessage(context, clickAction.name)
        }
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