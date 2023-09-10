package the.store.presentation.primary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import the.store.presentation.primary.views.pager_views.PrimaryViewPagerContent
import the.store.presentation.primary.views.bottom_sheet_views.PrimaryBottomSheetView

@Composable
fun PrimaryScreen(
    navController: NavHostController, viewModel: PrimaryViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        PrimaryBottomSheetView(viewModel) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                PrimaryViewPagerContent()
            }
        }
    }
}

@Preview
@Composable
fun PrimaryScreenPreview() {
    PrimaryScreen(navController = rememberNavController())
}