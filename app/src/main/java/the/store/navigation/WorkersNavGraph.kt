package the.store.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.core.domain.constants.Constants
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.navigation.base.BottomBarScreen
import the.store.presentation.workers.WorkersScreen
import the.store.presentation.workers.WorkersViewModel
import the.store.presentation.workers.create_edit_worker.CreateEditWorkerScreen
import the.store.presentation.workers.models.WorkersUiEvent

fun NavGraphBuilder.workersNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.Workers.route,
        startDestination = BottomBarScreen.Workers.route
    ) {
        composable(route = BottomBarScreen.Workers.route) {
            val viewModel = hiltViewModel<WorkersViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            WorkersScreen(
                uiState = uiState,
                initUi = {
                    viewModel.onTriggerEvent(WorkersUiEvent.InitUiScreen)
                },
                createWorker = {
                    navController.navigate(Screen.Worker.setUserData(0L)) {
                        launchSingleTop = true
                    }
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
                    }
                }
            )
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
