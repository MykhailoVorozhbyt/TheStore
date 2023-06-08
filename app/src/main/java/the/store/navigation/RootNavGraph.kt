package the.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.core.navigation.Graph

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Graph.Login.route) {
        loginNavGraph(navController)
        mainNavGraph(navController)
    }
}