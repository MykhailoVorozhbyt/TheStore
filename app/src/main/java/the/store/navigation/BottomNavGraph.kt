package the.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.navigation.Graph
import com.example.core.navigation.base.BottomBarScreen
import the.store.presentation.primary.PrimaryScreen

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
        productsNavGraph(navController)
        basketNavGraph(navController)
        workersNavGraph(navController)
        moreNavGraph(navController)
    }

}