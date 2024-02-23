package the.store.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import the.store.presentation.basket.BasketScreen
import the.store.presentation.basket.BasketViewModel


fun NavGraphBuilder.basketNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.Basket.route,
        startDestination = Screen.Basket.route
    ) {
        composable(route = Screen.Basket.route) {
            val viewModel = hiltViewModel<BasketViewModel>()
            BasketScreen(
                initUiContent = {

                },
                pressOnBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}