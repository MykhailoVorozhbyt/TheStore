package the.store.presentation.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.base.bottomTabs
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import the.store.navigation.BottomNavGraph
import the.store.utils.extensions.AddNavigationBarItem
import the.store.utils.extensions.bottomBarAnimatedScroll

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainScreenPreview() {
    MainScreen {}
}

@Composable
fun MainScreen(
    bottomBarNavController: NavHostController = rememberNavController(),
    logout: () -> Unit
) {
    val bottomBarHeight = 80.dp
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
    val buttonsVisible = remember { mutableStateOf(true) }
    LaunchedEffect(bottomBarOffsetHeightPx.value) {
        buttonsVisible.value = bottomBarOffsetHeightPx.value >= -10
    }
    Scaffold(
        modifier = Modifier.bottomBarAnimatedScroll(
            height = bottomBarHeight, offsetHeightPx = bottomBarOffsetHeightPx
        ),
        bottomBar = {
            BottomBar(navController = bottomBarNavController, bottomBarOffsetHeightPx)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            BottomNavGraph(bottomBarNavController) { logout.invoke() }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController, bottomBarOffsetHeightPx: MutableState<Float>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = bottomTabs.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
            containerColor = TheStoreColors.blackOrWhiteColor,
            //TODO: fixed or remove
            /*            modifier = Modifier
                            .offset {
                                IntOffset(
                                    x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt()
                                )
                            }*/
        ) {
            bottomTabs.forEach { screen ->
                AddNavigationBarItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}