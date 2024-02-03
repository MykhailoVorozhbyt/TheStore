package the.store.presentation.products

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.example.core.base.states.BaseViewState
import com.example.core.ui.widget.EmptyView
import com.example.core.ui.widget.ErrorView
import com.example.core.ui.widget.LoadingView
import com.example.core.utils.extensions.modifiers.cast
import the.store.presentation.products.models.ProductsUiState
import the.store.presentation.products.views.ProductsContent
import the.store.presentation.workers.views.AddTopAppBar

@Composable
fun ProductsScreen(
    uiState: BaseViewState<*>,
    initUi: () -> Unit,
    createProduct: () -> Unit,
    searchText: (String) -> Unit,
    productClick: (Long) -> Unit
) {
    AddTopAppBar(
        title = stringResource(
            id = com.example.theme.R.string.products
        ),
        addClick = {
            createProduct.invoke()
        }
    ) {
        when (uiState) {
            is BaseViewState.Data -> {
                val currentState = uiState.cast<BaseViewState.Data<ProductsUiState>>().value
                ProductsContent(
                    currentState,
                    searchText = searchText,
                    productClick = productClick
                )
            }

            is BaseViewState.Empty -> EmptyView()
            is BaseViewState.Loading -> LoadingView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    initUi.invoke()
                }
            )
        }
    }

    LaunchedEffect(key1 = true) {
        initUi.invoke()
    }
}

