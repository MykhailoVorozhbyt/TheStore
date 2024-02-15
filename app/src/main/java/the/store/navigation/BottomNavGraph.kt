package the.store.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.navigation.Graph
import com.example.core.navigation.base.BottomBarScreen
import the.store.presentation.primary.PrimaryScreen
import the.store.presentation.primary.PrimaryViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController, logout: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Primary.route,
        route = Graph.PrimaryScreen.route
    ) {
        composable(route = BottomBarScreen.Primary.route) {
            val viewModel = hiltViewModel<PrimaryViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            PrimaryScreen(
                state = uiState,
                initUiData = {
                    viewModel.initData()
                },
                searchSale = { text -> },
                itemClick = { id -> },
            )
        }
        productsNavGraph(navController)
        basketNavGraph(navController)
        workersNavGraph(navController)
        moreNavGraph(navController) {
            logout.invoke()
        }
    }

}