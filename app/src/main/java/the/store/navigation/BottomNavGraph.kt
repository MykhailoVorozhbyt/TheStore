package the.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.navigation.base.BottomBarScreen
import the.store.presentation.more.MoreScreen
import the.store.presentation.primary.PrimaryScreen
import the.store.presentation.products.ProductsScreen
import the.store.presentation.workers.WorkersScreen
import the.store.presentation.workers.create_edit_worker.CreateEditWorkerScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Primary.route,
        route = Graph.PrimaryScreen.route
    ) {
        composable(route = BottomBarScreen.Primary.route) {
            PrimaryScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Products.route) {
            ProductsScreen(navController = navController)
        }

        basketNavGraph(navController = navController)

        composable(route = BottomBarScreen.Workers.route) {
            WorkersScreen(navController = navController)
        }
        composable(route = BottomBarScreen.More.route) {
            MoreScreen(navController = navController)
        }

        composable(route = Screen.Worker.route) {
            CreateEditWorkerScreen(navController = navController)
        }
    }

}