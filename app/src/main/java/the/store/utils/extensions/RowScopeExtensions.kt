package the.store.utils.extensions

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.base.BottomBarScreen
import com.example.theme.TheStoreColors
import com.example.theme.whiteOrBlackColor
import com.example.theme.blackOrWhiteColor


@Composable
fun AddItemPreview() {
    val screens = listOf(
        BottomBarScreen.Primary,
        BottomBarScreen.Products,
        BottomBarScreen.Basket,
        BottomBarScreen.Workers,
        BottomBarScreen.More,
    )
    val navBackStackEntry by rememberNavController().currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(
        containerColor = TheStoreColors.blackOrWhiteColor,
    ) {
        screens.forEach { screen ->
            AddNavigationBarItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = rememberNavController(),
            )
        }
    }
}

@Composable
fun RowScope.AddNavigationBarItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        label = {
            Text(text = screen.title)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            if (currentDestination?.route != screen.route) {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = TheStoreColors.blackOrWhiteColor,
            selectedTextColor = TheStoreColors.whiteOrBlackColor,
            indicatorColor = TheStoreColors.whiteOrBlackColor,
            unselectedIconColor = TheStoreColors.whiteOrBlackColor,
            unselectedTextColor = TheStoreColors.whiteOrBlackColor,
        ),
        modifier = Modifier.padding(

        )
    )
}