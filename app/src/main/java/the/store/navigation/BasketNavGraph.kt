package the.store.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import the.store.presentation.basket.BasketScreen
import the.store.presentation.basket.BasketViewModel
import the.store.presentation.basket.models.BasketSheetUiEvent
import the.store.presentation.basket.models.BasketUiEvent


fun NavGraphBuilder.basketNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.Basket.route,
        startDestination = Screen.Basket.route
    ) {
        composable(route = Screen.Basket.route) {
            val viewModel = hiltViewModel<BasketViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            BasketScreen(
                state = uiState,
                //Basket
                initUiContent = {
                    viewModel.onTriggerEvent(BasketUiEvent.InitUi)
                },
                pressOnBack = { navController.popBackStack() },
                searchProduct = {
                    viewModel.onTriggerEvent(BasketUiEvent.SearchProduct(it))
                },
                productClick = {
                    viewModel.onTriggerEvent(BasketUiEvent.ProductClick(it))
                },
                //Sheet
                salleClick = {
                    viewModel.onTriggerEvent(BasketSheetUiEvent.SalleClick)
                },
                deleteAllProductsClick = {
                    viewModel.onTriggerEvent(BasketSheetUiEvent.DeleteAllProductsClick)
                },
                deleteProductClick = {
                    viewModel.onTriggerEvent(BasketSheetUiEvent.DeleteProductClick(it))
                },
                plusQuantity = {

                },
                minusQuantity = {

                }
            )
        }
    }
}