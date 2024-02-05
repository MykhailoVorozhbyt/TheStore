package the.store.presentation.more

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.enums.MoreScreenClickAction
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import the.store.presentation.more.models.MoreScreenUiState
import the.store.presentation.more.views.MoreScreenContent

@Composable
fun MoreScreen(
    state: MoreScreenUiState,
    initUiContent: () -> Unit,
    itemClick: (MoreScreenClickAction) -> Unit,
    exitClick: () -> Unit,
) {
    MoreScreenBody(exitClick) {
        when {
            state.isLoading -> {
                LoadingView()
            }

            state.isExitButtonClicked -> {
                exitClick.invoke()
            }

            state.screenUi.isNotEmpty() -> {
                MoreScreenContent(state.screenUi) {
                    itemClick.invoke(it)
                }
            }
        }
    }
    LaunchedEffect(key1 = true) {
        initUiContent.invoke()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreenBody(
    exitClick: () -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.more),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(),
                        color = TheStoreColors.blackOrWhiteColor,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = TheStoreColors.whiteOrBlackColor
                ),
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    IconButton(
                        onClick = {
                            exitClick.invoke()
                        }
                    ) {
                        Icon(
                            rememberVectorPainter(Icons.Filled.ArrowForward),
                            contentDescription = null,
                            tint = TheStoreColors.blackOrWhiteColor,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            )
        },
        content = { pageContent.invoke(it) }
    )
}