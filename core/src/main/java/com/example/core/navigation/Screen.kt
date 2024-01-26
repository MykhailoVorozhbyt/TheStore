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

    object Registration :
        BaseScreen("registration/{${Constants.USER_PHONE_NUMBER}}/{${Constants.USER_PASSWORD}}") {
        fun setUserData(phone: String, password: String): String {
            return "registration/${phone}/${password}"
        }
    }

    object Primary : BaseScreen("primary_screen")

    object Products : BaseScreen("products_screen")
    object Product : BaseScreen("product_screen/{${Constants.PRODUCT_ID}}") {
        fun setProductId(id: Long): String {
            return "product_screen/${id}"
        }
    }

    object Basket : BaseScreen("basket_screen")

    object Workers : BaseScreen("workers_screen")
    object Worker : BaseScreen("worker_screen/{${Constants.WORKER_ID}}") {
        fun setUserData(id: Long): String {
            return "worker_screen/${id}"
        }
    }

    object More : BaseScreen("more_screen")
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
