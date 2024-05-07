package the.store.presentation.primary

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.utils.extensions.modifiers.baseTopRoundedCornerShape
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.core.utils.extensions.modifiers.defaultTopPadding
import com.example.core.utils.helpers.showMessage
import com.example.theme.BlackBoldTextStyle
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import kotlinx.coroutines.launch
import the.store.presentation.primary.models.PrimaryUiState
import the.store.presentation.primary.views.bottom_sheet_views.PrimaryBottomSheetContent
import the.store.presentation.primary.views.pager_views.PrimaryViewPagerContent
import the.store.utils.TOperation
import the.store.utils.UnitOperation

@PreviewLightDark
@PreviewScreenSizes
@Composable
fun PrimaryScreenPreview() {
    PrimaryScreen(PrimaryUiState(), {}, {}) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryScreen(
    state: PrimaryUiState,
    initUiData: UnitOperation,
    searchSale: TOperation<String>,
    itemClick: TOperation<Long>,
) {
    val context = LocalContext.current
    var columnHeightDp by remember {
        mutableStateOf(100.dp)
    }
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        confirmValueChange = { it != SheetValue.Hidden },
        skipHiddenState = false,
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState)

    BottomSheetScaffold(
        sheetShape = baseTopRoundedCornerShape(),
        sheetPeekHeight = columnHeightDp,
        sheetShadowElevation = 20.dp,
        scaffoldState = bottomSheetScaffoldState,
        containerColor = TheStoreColors.blackOrWhiteColor,
        sheetContainerColor = TheStoreColors.whiteOrBlackColor,
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        coroutineScope.launch {
                            when {
                                bottomSheetScaffoldState.bottomSheetState.hasExpandedState -> {
                                    bottomSheetScaffoldState.bottomSheetState.partialExpand()
                                }
                            }
                        }
                    }
                )
            },
        topBar = {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TheStoreColors.blackOrWhiteColor)
                    .defaultTopPadding()
                    .defaultHorizontalPadding(),
                style = BlackBoldTextStyle(18),
                color = TheStoreColors.whiteOrBlackColor
            )
        },
        sheetContent = {
            PrimaryBottomSheetContent(
                list = state.history,
                historySearch = state.historySearch,
                searchText = {
                    searchSale.invoke(it)
                },
                itemClick = {
                    itemClick.invoke(it)
                },
            )
        }
    ) {
        if (state.error != null) {
            showMessage(context, state.error)
        }
        PrimaryScreenContent(state) {
            columnHeightDp = it
        }
    }
    LaunchedEffect(true) {
        initUiData.invoke()
    }
}

@Composable
fun PrimaryScreenContent(
    state: PrimaryUiState,
    sizeForBottomSheet: (Dp) -> Unit
) {
    val localDensity = LocalDensity.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        PrimaryViewPagerContent(state)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .onGloballyPositioned { coordinates ->
                    sizeForBottomSheet.invoke(
                        with(localDensity) {
                            coordinates.size.height.toDp()
                        }
                    )
                }
        )
    }
}