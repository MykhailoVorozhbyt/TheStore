package the.store.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.navigation.base.BottomBarScreen
import com.example.core.utils.enums.MoreScreenClickAction
import the.store.presentation.company.CompanyScreen
import the.store.presentation.company.CompanyViewModel
import the.store.presentation.company.models.OwnerUiEvent
import the.store.presentation.more.MoreScreen
import the.store.presentation.more.MoreScreenViewModel
import the.store.presentation.more.models.MoreScreenUiEvent

fun NavGraphBuilder.moreNavGraph(
    navController: NavHostController,
    logout: () -> Unit
) {
    navigation(
        route = Graph.More.route,
        startDestination = BottomBarScreen.More.route
    ) {
        composable(route = BottomBarScreen.More.route) {
            val viewModel = hiltViewModel<MoreScreenViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            MoreScreen(
                uiState,
                initUiContent = {
                    viewModel.onTriggerEvent(
                        MoreScreenUiEvent.InitUiContent
                    )
                },
                itemClick = {
                    moreScreenItemClick(navController, it)
                },
                exitClick = {
                    logout.invoke()
//                    navController.navigate(Graph.Root.route) {
//                        popUpTo(Graph.PrimaryScreen.route) {
//                            inclusive = true
//                        }
//                    }
                },
            )
        }
        composable(route = Screen.Company.route) {
            val viewModel = hiltViewModel<CompanyViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            CompanyScreen(
                uiState,
                initUiContent = {
                    viewModel.onTriggerEvent(OwnerUiEvent.InitUiContent)
                },
                editClick = {
                    viewModel.onTriggerEvent(OwnerUiEvent.SubmitEditClick)
                },
                pressOnBack = {
                    navController.popBackStack()
                },
                photoChanged = {
                    viewModel.onTriggerEvent(OwnerUiEvent.PhotoChanged(it))
                },
                deletePhoto = {
                    viewModel.onTriggerEvent(OwnerUiEvent.DeletePhotoUri)
                },
                nameChanged = {
                    viewModel.onTriggerEvent(OwnerUiEvent.CompanyNameChanged(it))
                },
                descriptionChanged = {
                    viewModel.onTriggerEvent(OwnerUiEvent.DescriptionChanged(it))
                }
            )
        }
    }
}


private fun moreScreenItemClick(
    navController: NavHostController,
    clickAction: MoreScreenClickAction,
) {
    when (clickAction) {
        MoreScreenClickAction.CompanyClick -> {
            navController.navigate(Screen.Company.route)
        }

        MoreScreenClickAction.CopyTheDeviceIdClick -> {
        }
    }
}
