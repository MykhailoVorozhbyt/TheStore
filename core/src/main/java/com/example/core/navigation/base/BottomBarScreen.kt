package com.example.core.navigation.base

import androidx.annotation.DrawableRes
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    data object Primary : BottomBarScreen(
        route = Screen.Primary.route,
        title = "Primary",
        icon = com.example.theme.R.drawable.ic_home
    )

    data object Products : BottomBarScreen(
        route = Screen.Products.route,
        title = "Products",
        icon = com.example.theme.R.drawable.ic_list
    )

    data object Basket : BottomBarScreen(
        route = Graph.Basket.route,
        title = "Basket",
        icon = com.example.theme.R.drawable.ic_shopping_basket
    )

    data object Workers : BottomBarScreen(
        route = Screen.Workers.route,
        title = "Workers",
        icon = com.example.theme.R.drawable.ic_persons
    )

    data object More : BottomBarScreen(
        route = Screen.More.route,
        title = "More",
        icon = com.example.theme.R.drawable.ic_more_horiz
    )

}

val bottomTabs by lazy {
    listOf(
        BottomBarScreen.Primary,
        BottomBarScreen.Products,
        BottomBarScreen.Basket,
        BottomBarScreen.Workers,
        BottomBarScreen.More,
    )
}