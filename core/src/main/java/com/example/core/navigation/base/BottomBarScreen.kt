package com.example.core.navigation.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.core.navigation.Screen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Primary : BottomBarScreen(
        route = Screen.Primary.route,
        title = "Main",
        icon = Icons.Default.Home,
    )

    object Goods : BottomBarScreen(
        route = Screen.Goods.route,
        title = "Goods",
        icon = Icons.Default.List,
    )

    object Basket : BottomBarScreen(
        route = Screen.Basket.route,
        title = "Basket",
        icon = Icons.Default.Settings,
    )

    object Workers : BottomBarScreen(
        route = Screen.Workers.route,
        title = "Workers",
        icon = Icons.Default.Person,
    )

    object More : BottomBarScreen(
        route = Screen.More.route,
        title = "More",
        icon = Icons.Default.MoreVert,
    )

}