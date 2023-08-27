package the.store.presentation.primary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import the.store.presentation.main.ComposableHelloText
import the.store.presentation.primary.pager_views.PrimaryViewPagerContent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrimaryScreen(
    navController: NavHostController,
    viewModel: PrimaryViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(3)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            PrimaryViewPagerContent()
            ComposableHelloText("PrimaryScreen")
        }
    }
}

@Preview
@Composable
fun PrimaryScreenPreview() {
    PrimaryScreen(navController = rememberNavController())
}