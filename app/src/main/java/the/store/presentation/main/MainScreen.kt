package the.store.presentation.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.core.navigation.base.BottomBarScreen
import com.example.theme.R
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import the.store.navigation.BottomNavGraph
import the.store.utils.extensions.AddItem

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(navController: NavHostController = rememberAnimatedNavController()) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            BottomNavGraph(navController)
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 18.sp,
                color = Color.White
            )
        },
        backgroundColor = colorResource(id = R.color.black),
        contentColor = Color.White,
        actions = {

        }
    )
}

@Composable
fun ComposableHelloText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Primary,
        BottomBarScreen.Products,
        BottomBarScreen.Basket,
        BottomBarScreen.Workers,
        BottomBarScreen.More,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}