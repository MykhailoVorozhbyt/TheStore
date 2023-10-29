package the.store.presentation.primary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.base.views.BaseButton
import com.example.core.utils.extensions.modifiers.smallHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallPadding
import com.example.theme.R
import the.store.presentation.primary.models.PrimaryUiEvent
import the.store.presentation.primary.views.bottom_sheet_views.PrimaryBottomSheetView
import the.store.presentation.primary.views.pager_views.PrimaryViewPagerContent

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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .smallHorizontalPadding()
                        .background(colorResource(id = R.color.errorColor)),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    BaseButton(
                        text = stringResource(id = R.string.x_report),
                        buttonModifier = Modifier
                            .weight(1f)
                            .smallPadding()
                            .height(60.dp),
                        revertColor = false
                    ) {
                        viewModel.onTriggerEvent(PrimaryUiEvent.SubmitXReportClick)
                    }
                    BaseButton(
                        text = stringResource(id = R.string.open_shift),
                        buttonModifier = Modifier
                            .weight(1f)
                            .smallPadding()
                            .height(60.dp),
                        revertColor = false
                    ) {
                        viewModel.onTriggerEvent(PrimaryUiEvent.SubmitShiftReportClick)
                    }
                    BaseButton(
                        text = stringResource(id = R.string.copy_reports),
                        buttonModifier = Modifier
                            .weight(1f)
                            .smallPadding()
                            .height(60.dp),
                        revertColor = false
                    ) {
                        viewModel.onTriggerEvent(PrimaryUiEvent.SubmitCopyReportsClick)
                    }
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