package com.example.core.navigation

import com.example.core.navigation.base.BaseScreen

sealed class Screen(route: String) : BaseScreen(route) {

    object AnimatedSplash : BaseScreen("animated_splash_screen")
    object Login : BaseScreen("login_screen")
    object InputPassword : BaseScreen("input_password_screen")
    object AvailableCashDesks : BaseScreen("available_cash_desks_screen")


    object Primary : BaseScreen("primary_screen")
    object Goods : BaseScreen("goods_screen")
    object Basket : BaseScreen("basket_screen")
    object Workers : BaseScreen("workers_screen")
    object More : BaseScreen("mode_screen")
}