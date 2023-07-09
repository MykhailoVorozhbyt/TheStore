package the.store.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import the.store.presentation.available_cash_desks.AvailableCashDesksScreen
import the.store.presentation.input_password.InputPasswordScreen
import the.store.presentation.login.LoginScreen
import the.store.presentation.splash.AnimatedSplashScreen

fun NavGraphBuilder.loginNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.Login.route, startDestination = Screen.AnimatedSplash.route
    ) {
        composable(route = Screen.AnimatedSplash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.InputPassword.route,
            /*           arguments = listOf(
                           navArgument(Constants.USER_PHONE_NUMBER) {
                               type = NavType.StringType
                           }
                       )*/
        ) { backStackEntry ->
//            val userPhoneNumber =
//                backStackEntry.arguments?.getString(Constants.USER_PHONE_NUMBER).elvis()
            InputPasswordScreen(navController = navController)
        }
        composable(route = Screen.AvailableCashDesks.route) {
            AvailableCashDesksScreen(navController = navController)
        }
    }
}