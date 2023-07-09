package com.example.core.navigation

import com.example.core.domain.constants.Constants
import com.example.core.navigation.base.BaseScreen

sealed class Screen(route: String) : BaseScreen(route) {

    object AnimatedSplash : BaseScreen("animated_splash_screen")
    object Login : BaseScreen("login_screen")
    object InputPassword : BaseScreen("input_password_screen/{${Constants.USER_PHONE_NUMBER}}") {
        fun setUserPhoneNumber(phone: String): String {
            return this.route.replace(
                oldValue = "{${Constants.USER_PHONE_NUMBER}}",
                newValue = phone
            )
        }
    }

    object Registration : BaseScreen("registration")

    object AvailableCashDesks : BaseScreen("available_cash_desks_screen")

    object Primary : BaseScreen("primary_screen")
    object Products : BaseScreen("goods_screen")
    object Workers : BaseScreen("workers_screen")
    object More : BaseScreen("mode_screen")

    object Basket : BaseScreen("basket_screen")
}

fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}
