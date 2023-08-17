package the.store.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import the.store.presentation.basket.BasketScreen


fun NavGraphBuilder.basketNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.Basket.route,
        startDestination = Screen.Basket.route
    ) {
        composable(route = Screen.Basket.route) {
            BasketScreen(navController = navController)
        }
    }

}