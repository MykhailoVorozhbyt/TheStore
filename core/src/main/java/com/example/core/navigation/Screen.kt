package com.example.core.navigation

import com.example.core.navigation.screens.BaseScreen

sealed class Screen(route: String) : BaseScreen(route) {

    object AnimatedSplash : BaseScreen("animated_splash_screen")
    object Login : BaseScreen("login_screen")
    object InputPassword : BaseScreen("input_password_screen")
    object AvailableCashDesks : BaseScreen("available_cash_desks_screen")


    object Main : BaseScreen("home_screen")
}