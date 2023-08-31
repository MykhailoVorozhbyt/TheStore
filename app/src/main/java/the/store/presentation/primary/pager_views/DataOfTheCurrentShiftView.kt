package the.store.presentation.primary.pager_views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.base.views.BaseCardView
import com.example.core.base.views.ExtraSmallHorizontalSpacer
import com.example.core.utils.extensions.modifiers.defaultTextPadding
import com.example.core.utils.extensions.modifiers.smallPadding
import com.example.core.utils.singletons.WorkerSingleton
import com.example.theme.R
import com.example.theme.WhiteBoldTextStyle
import com.example.theme.WhiteTextStyle

@Preview
@Composable
fun SDataOfTheCurrentShiftViewPreview() {
    DataOfTheCurrentShiftView()
}

@Composable
fun DataOfTheCurrentShiftView() {
    lateinit var workerSingleton: WorkerSingleton

    BaseCardView {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
//            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            ExtraSmallHorizontalSpacer()
            Text(
                text = stringResource(id = R.string.data_of_the_current_shift),
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultTextPadding(),
                style = WhiteBoldTextStyle,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .smallPadding(),
            ) {
                Text(
                    text = stringResource(
                        id = R.string.cashier_with_dots_and_value,
                        "Current Worker"
                    ),
                    style = WhiteTextStyle,
                    textAlign = TextAlign.Start
                )
            }
            ExtraSmallHorizontalSpacer()
        }
    }
}