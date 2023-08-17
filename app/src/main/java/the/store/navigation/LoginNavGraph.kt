package the.store.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.core.domain.constants.Constants
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import com.example.core.utils.elvis
import the.store.presentation.login_to_app.login.LoginScreen
import the.store.presentation.login_to_app.registration.RegistrationScreen
import the.store.presentation.login_to_app.splash.AnimatedSplashScreen

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
//        composable(
//            route = Screen.InputPassword.route,
//            /*           arguments = listOf(
//                           navArgument(Constants.USER_PHONE_NUMBER) {
//                               type = NavType.StringType
//                           }
//                       )*/
//        ) { backStackEntry ->
////            val userPhoneNumber =
////                backStackEntry.arguments?.getString(Constants.USER_PHONE_NUMBER).elvis()
//            InputPasswordScreen(navController = navController)
//        }
//        composable(route = Screen.AvailableCashDesks.route) {
//            AvailableCashDesksScreen(navController = navController)
//        }

        composable(
            route = Screen.Registration.route,
            arguments = listOf(
                navArgument(Constants.USER_PHONE_NUMBER) {
                    type = NavType.StringType
                },
                navArgument(Constants.USER_PASSWORD) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            RegistrationScreen(
                navController = navController,
                bundle = backStackEntry.arguments.elvis()
            )
        }
    }
}