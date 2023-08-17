package com.example.core.navigation.base

import androidx.annotation.DrawableRes
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Primary : BottomBarScreen(
        route = Screen.Primary.route,
        title = "Primary",
        icon = com.example.theme.R.drawable.ic_home
    )

    object Products : BottomBarScreen(
        route = Screen.Products.route,
        title = "Goods",
        icon = com.example.theme.R.drawable.ic_list
    )

    object Basket : BottomBarScreen(
        route = Graph.Basket.route,
        title = "Basket",
        icon = com.example.theme.R.drawable.ic_shopping_basket
    )

    object Workers : BottomBarScreen(
        route = Screen.Workers.route,
        title = "Workers",
        icon = com.example.theme.R.drawable.ic_person
    )

    object More : BottomBarScreen(
        route = Screen.More.route,
        title = "More",
        icon = com.example.theme.R.drawable.ic_more_horiz
    )

}
