package the.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.navigation.Graph
import the.store.presentation.main.MainScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController,
        route = Graph.Root.route,
        startDestination = Graph.Login.route
    ) {
        loginNavGraph(navController)
        composable(route = Graph.Primary.route) {
            MainScreen()
        }
    }
}