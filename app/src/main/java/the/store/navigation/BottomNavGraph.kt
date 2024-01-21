package the.store.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core.domain.constants.Constants
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.navigation.base.BottomBarScreen
import the.store.presentation.more.MoreScreen
import the.store.presentation.primary.PrimaryScreen
import the.store.presentation.products.ProductsScreen
import the.store.presentation.products.ProductsViewModel
import the.store.presentation.products.models.ProductsUiEvent
import the.store.presentation.workers.WorkersScreen
import the.store.presentation.workers.WorkersViewModel
import the.store.presentation.workers.create_edit_worker.CreateEditWorkerScreen
import the.store.presentation.workers.models.WorkersUiEvent

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
            val viewModel = hiltViewModel<ProductsViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            ProductsScreen(
                uiState,
                initUi = {
                    viewModel.onTriggerEvent(ProductsUiEvent.InitUiScreen)
                },
                createProduct = {
//                    viewModel.onTriggerEvent(ProductsUiEvent.)
                },
                searchText = {
                    viewModel.onTriggerEvent(ProductsUiEvent.InputValueChanged(it))
                },
                productClick = {
//                    viewModel.onTriggerEvent(ProductsUiEvent.InitUiScreen)
                },
                refreshAction = {
                    viewModel.onTriggerEvent(ProductsUiEvent.RefreshList)
                },
            )
        }

        basketNavGraph(navController = navController)

        composable(route = BottomBarScreen.Workers.route) {
            val viewModel = hiltViewModel<WorkersViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            WorkersScreen(
                uiState = uiState,
                initUi = {
                    viewModel.onTriggerEvent(WorkersUiEvent.InitUiScreen)
                },
                createWorker = {
                    navController.navigate(Screen.Worker.setUserData(0L))
                },
                searchText = {
                    viewModel.onTriggerEvent(
                        WorkersUiEvent.InputValueChanged(
                            it
                        )
                    )
                },
                workerClick = {
                    navController.navigate(Screen.Worker.setUserData(it)) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                refreshAction = {
                    viewModel.onTriggerEvent(
                        WorkersUiEvent.RefreshList
                    )
                },
            )
        }
        composable(route = BottomBarScreen.More.route) {
            MoreScreen(navController = navController)
        }

        composable(route = Screen.Worker.route,
            arguments = listOf(
                navArgument(Constants.WORKER_ID) {
                    type = NavType.LongType
                }
            )) {
            val workerId = it.arguments?.getLong(Constants.WORKER_ID) ?: 0L
            CreateEditWorkerScreen(navController = navController, workerId = workerId)
        }
    }

}