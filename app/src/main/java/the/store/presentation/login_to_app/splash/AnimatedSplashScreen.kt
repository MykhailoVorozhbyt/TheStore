package the.store.presentation.login_to_app.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.Graph
import com.example.core.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(
    navController: NavHostController,
    viewModel: AnimatedSplashViewModel = hiltViewModel()
) {

    val userIsLoggedIn by remember {
        viewModel.userIsLoggedIn
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(1000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        navController.popBackStack()
        if (userIsLoggedIn.not()) {
            navController.navigate(Screen.Login.route)
        } else {
            navController.navigate(Graph.Primary.route) {
                popUpTo(Graph.Login.route)
            }
        }
    }
    SplashScreen(alphaAnimation.value)
}

@Composable
private fun SplashScreen(alpha: Float) {
    Box(
        modifier = Modifier
            .background(colorResource(id = com.example.theme.R.color.white))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha),
            painter = painterResource(id = com.example.theme.R.drawable.ic_the_store),
            contentDescription = "Logo icon",
        )
    }
}

@Preview
@Composable
fun AnimatedSplashScreenPreview() {
    val navController = rememberNavController()
    AnimatedSplashScreen(navController)
}