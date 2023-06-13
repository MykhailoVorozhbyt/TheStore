package the.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.navigation.Graph
import com.example.core.navigation.base.BottomBarScreen
import the.store.presentation.basket.BasketScreen
import the.store.presentation.goods.GoodsScreen
import the.store.presentation.more.MoreScreen
import the.store.presentation.primary.PrimaryScreen
import the.store.presentation.workers.WorkersScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Primary.route,
        route = Graph.Primary.route
    ) {
        composable(route = BottomBarScreen.Primary.route) {
            PrimaryScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Goods.route) {
            GoodsScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Basket.route) {
            BasketScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Workers.route) {
            WorkersScreen(navController = navController)
        }
        composable(route = BottomBarScreen.More.route) {
            MoreScreen(navController = navController)
        }
    }

}