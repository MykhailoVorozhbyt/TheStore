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
        startDestination = Screen.AnimatedSplash.route,
        route = Graph.Login.route
    ) {
        composable(route = Screen.AnimatedSplash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.InputPassword.route) {
            InputPasswordScreen(navController = navController)
        }
        composable(route = Screen.AvailableCashDesks.route) {
            AvailableCashDesksScreen(navController = navController)
        }
    }
}