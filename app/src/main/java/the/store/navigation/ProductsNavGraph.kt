package the.store.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.core.domain.constants.Constants
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.navigation.base.BottomBarScreen
import the.store.presentation.products.ProductsScreen
import the.store.presentation.products.ProductsViewModel
import the.store.presentation.products.models.ProductsUiEvent
import the.store.presentation.products.product.ProductScreen
import the.store.presentation.products.product.ProductViewModel
import the.store.presentation.products.product.models.ProductUiEvent

fun NavGraphBuilder.productsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.Products.route,
        startDestination = BottomBarScreen.Products.route
    ) {
        composable(route = BottomBarScreen.Products.route) {
            val viewModel = hiltViewModel<ProductsViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            ProductsScreen(
                uiState,
                initUi = {
                    viewModel.onTriggerEvent(ProductsUiEvent.InitUiScreen)
                },
                createProduct = {
                    navController.navigate(Screen.Product.setProductId(0L)) {
                        launchSingleTop = true
                    }
                },
                searchText = {
                    viewModel.onTriggerEvent(ProductsUiEvent.InputValueChanged(it))
                },
                productClick = {
                    navController.navigate(Screen.Product.setProductId(it)) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = Screen.Product.route,
            arguments = listOf(
                navArgument(Constants.PRODUCT_ID) {
                    type = NavType.LongType
                }
            )
        ) {
            val viewModel = hiltViewModel<ProductViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val productId = it.arguments?.getLong(Constants.PRODUCT_ID) ?: 0L
            ProductScreen(
                productId = productId,
                uiState = uiState,
                pressOnBack = {
                    navController.popBackStack()
                },
                initUi = { id ->
                    viewModel.onTriggerEvent(ProductUiEvent.InitUiContent(id))
                },
                productActionClick = {
                    viewModel.onTriggerEvent(ProductUiEvent.SubmitCreateEditClick)
                },
                photoChanged = { uri ->
                    viewModel.onTriggerEvent(ProductUiEvent.PhotoChanged(uri))
                },
                deletePhotoUri = {
                    viewModel.onTriggerEvent(ProductUiEvent.DeletePhotoUri)
                },
                nameChanged = { name ->
                    viewModel.onTriggerEvent(ProductUiEvent.NameChanged(name))
                },
                priceChanged = { price ->
                    viewModel.onTriggerEvent(ProductUiEvent.PriceChanged(price))
                },
                measurementsChanged = { measurementsId ->
                    viewModel.onTriggerEvent(ProductUiEvent.MeasurementsChanged(measurementsId))
                },
                currencyChanged = { currency ->
                    viewModel.onTriggerEvent(ProductUiEvent.CurrencyChanged(currency))
                },
                descriptionChanged = { description ->
                    viewModel.onTriggerEvent(ProductUiEvent.DescriptionChanged(description))
                },
                barcodeChanged = { barcode ->
                    viewModel.onTriggerEvent(ProductUiEvent.BarcodeChanged(barcode))
                },
                deleteProduct = { id ->
                    viewModel.onTriggerEvent(ProductUiEvent.DeleteProductClick(id))
                }
            )
        }
    }
}
