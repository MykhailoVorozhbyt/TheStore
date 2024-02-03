package the.store.presentation.products.product

import android.content.Context
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
import com.example.theme.R
import the.store.presentation.products.product.models.ProductUiState
import the.store.presentation.products.product.views.ProductContent


@Composable
fun ProductScreen(
    productId: Long,
    uiState: BaseViewState<*>,
    pressOnBack: () -> Unit,
    initUi: (Long) -> Unit,
    productActionClick: () -> Unit,
    photoChanged: (Uri) -> Unit,
    deletePhotoUri: () -> Unit,
    nameChanged: (String) -> Unit,
    priceChanged: (String) -> Unit,
    measurementsChanged: (Long) -> Unit,
    currencyChanged: (Long) -> Unit,
    descriptionChanged: (String) -> Unit,
    barcodeChanged: (String) -> Unit,
    deleteProduct: (Long) -> Unit,
) {
    val context: Context = LocalContext.current

    CreateEditContentBody(
        com.example.theme.R.string.product,
        pressOnBack = {
            pressOnBack.invoke()
        },
        editCreateClick = {
            productActionClick.invoke()
        }
    ) {
        when (uiState) {
            is BaseViewState.Data -> {
                val currentState = uiState.cast<BaseViewState.Data<ProductUiState>>().value
                if (currentState.actionProduct) {
                    DisposableEffect(uiState) {
                        pressOnBack.invoke()
                        when (currentState.id) {
                            0L -> {
                                showMessage(context, R.string.product_created_successfully)
                            }

                            else -> {
                                showMessage(context, R.string.product_data_updated_successfully)
                            }
                        }
                        onDispose {}
                    }
                }
                if (currentState.deleteProduct) {
                    DisposableEffect(uiState) {
                        pressOnBack.invoke()
                        showMessage(context, R.string.product_deleted_successfully)
                        onDispose {}
                    }
                }
                ProductContent(
                    context = context,
                    state = currentState,
                    photoChanged = photoChanged,
                    deletePhotoUri = deletePhotoUri,
                    nameChanged = nameChanged,
                    priceChanged = priceChanged,
                    measurementsChanged = measurementsChanged,
                    currencyChanged = currencyChanged,
                    descriptionChanged = descriptionChanged,
                    barcodeChanged = barcodeChanged,
                    deleteProduct = deleteProduct
                )
            }

            is BaseViewState.Empty -> EmptyView()
            is BaseViewState.Loading -> LoadingView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    initUi.invoke(productId)
                }
            )
        }
    }
    LaunchedEffect(key1 = true) {
        initUi.invoke(productId)
    }
}