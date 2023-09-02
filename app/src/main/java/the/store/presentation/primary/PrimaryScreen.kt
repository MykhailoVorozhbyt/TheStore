package the.store.presentation.primary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.base.views.BaseButton
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallVerticalPadding
import com.example.theme.R
import the.store.presentation.primary.pager_views.PrimaryViewPagerContent
import the.store.presentation.primary.views.PrimaryBottomSheet

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrimaryScreen(
    navController: NavHostController, viewModel: PrimaryViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        PrimaryBottomSheet {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                PrimaryViewPagerContent()
                BaseButton(
                    text = stringResource(id = R.string.shift_action),
                    buttonModifier = Modifier
                        .fillMaxWidth()
                        .smallVerticalPadding()
                        .defaultHorizontalPadding(),
                    revertColor = false
                ) {
                }
            }
        }
    }
}

@Preview
@Composable
fun PrimaryScreenPreview() {
    PrimaryScreen(navController = rememberNavController())
}