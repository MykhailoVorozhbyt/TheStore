package the.store.presentation.basket

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.core.ui.widget.TheStoreOnBackCenterAlignedTopAppBar
import com.example.core.utils.extensions.modifiers.baseTopRoundedCornerShape
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.whiteOrBlackColor
import kotlinx.coroutines.launch
import the.store.presentation.basket.views.BasketContent
import the.store.presentation.basket.views.BasketSheetContent
import the.store.presentation.basket.views.BasketSheetContentData
import the.store.presentation.products.models.productList

@PreviewLightDark
@Composable
fun BasketScreenPreview() {
    BasketScreen(
        {

        }, {

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(
    initUiContent: () -> Unit,
    pressOnBack: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        confirmValueChange = { it != SheetValue.Hidden },
        skipHiddenState = false,
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState)

    BottomSheetScaffold(
        sheetShape = baseTopRoundedCornerShape(),
        sheetShadowElevation = 10.dp,
        scaffoldState = bottomSheetScaffoldState,
        containerColor = TheStoreColors.whiteOrBlackColor,
        sheetContainerColor = TheStoreColors.whiteOrBlackColor,
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.hasExpandedState) {
                                bottomSheetScaffoldState.bottomSheetState.partialExpand()
                            }
                        }
                    }
                )
            },
        topBar = {
            TheStoreOnBackCenterAlignedTopAppBar(
                R.string.basket,
                pressOnBack = {
                    pressOnBack.invoke()
                }
            )
        },
        sheetContent = {
            BasketSheetContent(BasketSheetContentData)
        }
    ) {
        BasketContent(productList())
    }
    LaunchedEffect(Unit) {
        initUiContent.invoke()
    }
}