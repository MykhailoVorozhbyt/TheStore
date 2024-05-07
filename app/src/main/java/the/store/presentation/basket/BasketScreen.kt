package the.store.presentation.basket

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.core.base.states.BaseViewState
import com.example.core.domain.entities.ProductEntity
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.ErrorView
import com.example.core.ui.widget.LoadingView
import com.example.core.ui.widget.TheStoreOnBackCenterAlignedTopAppBar
import com.example.core.utils.extensions.modifiers.baseTopRoundedCornerShape
import com.example.core.utils.extensions.modifiers.cast
import com.example.core.utils.helpers.showMessage
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.whiteOrBlackColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import the.store.presentation.basket.models.BasketUiState
import the.store.presentation.basket.views.BasketContent
import the.store.presentation.basket.views.BasketSheetContent
import the.store.utils.TOperation
import the.store.utils.UnitOperation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(
    state: BaseViewState<*>,
    //Basket
    initUiContent: UnitOperation,
    pressOnBack: UnitOperation,
    searchProduct: TOperation<String>,
    productClick: TOperation<ProductEntity>,
    //Sheet
    salleClick: UnitOperation,
    deleteAllProductsClick: UnitOperation,
    deleteProductClick: TOperation<Long>,
    plusQuantity: TOperation<Long>,
    minusQuantity: TOperation<Long>,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        confirmValueChange = { it != SheetValue.Hidden },
        skipHiddenState = false,
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState)

    when (state) {
        is BaseViewState.Data -> {
            val currentState = state.cast<BaseViewState.Data<BasketUiState>>().value

            if (currentState.isCheckSold) {
                DisposableEffect(true) {
                    pressOnBack.invoke()
                    showMessage(context, "Check is Sell")
                    onDispose {}
                }
            }

            BasketScreenContainer(
                currentState,
                bottomSheetScaffoldState,
                coroutineScope,
                pressOnBack,
                searchProduct = searchProduct,
                productClick = productClick,
                salleClick = salleClick,
                deleteAllProductsClick = deleteAllProductsClick,
                deleteProductClick = deleteProductClick,
                plusQuantity = plusQuantity,
                minusQuantity = minusQuantity,
            )
        }

        is BaseViewState.Empty -> EmptyView()
        is BaseViewState.Loading -> LoadingView()
        is BaseViewState.Error -> ErrorView(
            e = state.cast<BaseViewState.Error>().throwable,
            action = {
                pressOnBack.invoke()
            }
        )
    }

    LaunchedEffect(Unit) {
        initUiContent.invoke()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun BasketScreenContainerPreview() {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        confirmValueChange = { it != SheetValue.Hidden },
        skipHiddenState = false,
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState)

    BasketScreenContainer(
        BasketUiState(),
        bottomSheetScaffoldState,
        coroutineScope,
        {}, {}, {}, {}, {}, {}, {}, {},
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun BasketScreenContainer(
    state: BasketUiState,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
    pressOnBack: UnitOperation,
    //Basket
    searchProduct: TOperation<String>,
    productClick: TOperation<ProductEntity>,
    //Sheet
    salleClick: UnitOperation,
    deleteAllProductsClick: UnitOperation,
    deleteProductClick: TOperation<Long>,
    plusQuantity: TOperation<Long>,
    minusQuantity: TOperation<Long>,
) {
    BottomSheetScaffold(
        sheetShape = baseTopRoundedCornerShape(),
        sheetShadowElevation = 30.dp,
        sheetPeekHeight = 80.dp,
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
                titleResId = R.string.basket,
                pressOnBack = pressOnBack
            )
        },
        sheetContent = {
            BasketSheetContent(
                fullPrice = state.basketFullPrice,
                list = state.basketProducts,
                salleClick = salleClick,
                deleteAllProductsClick = deleteAllProductsClick,
                deleteProductClick = deleteProductClick,
                plusQuantity = plusQuantity,
                minusQuantity = minusQuantity,
            )
        }
    ) {
        BasketContent(
            state = state,
            searchText = searchProduct,
            productClick = productClick
        )
    }
}