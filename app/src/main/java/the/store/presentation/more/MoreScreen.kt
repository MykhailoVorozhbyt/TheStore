package the.store.presentation.more

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.core.navigation.Graph
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.enums.MoreScreenClickAction
import com.example.core.utils.helpers.showMessage
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import the.store.presentation.more.models.MoreScreenUiEvent

@Composable
fun MoreScreen(
    viewModel: MoreScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    MoreScreenBody(viewModel) {
        when {
            uiState.isLoading -> {
                LoadingView()
            }

            uiState.isExitButtonClicked -> {
                //TODO: fix navigation
                navController.navigate(Graph.Root.route) {
                    popUpTo(Graph.PrimaryScreen.route) {
                        inclusive = true
                    }
                }
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
    viewModel: MoreScreenViewModel,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.more),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(),
                        color = TheStoreColors.whiteOrBlackColor,
                    )
                },
                backgroundColor = TheStoreColors.blackOrWhiteColor,
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    Icon(
                        rememberVectorPainter(Icons.Filled.ArrowForward),
                        contentDescription = null,
                        tint = TheStoreColors.whiteOrBlackColor,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { viewModel.onTriggerEvent(MoreScreenUiEvent.ExitButtonClick) }

                    )
                }
            )
        },
        content = { pageContent.invoke(it) }
    )
}