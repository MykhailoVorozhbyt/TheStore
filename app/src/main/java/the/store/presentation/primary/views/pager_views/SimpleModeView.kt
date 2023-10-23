package the.store.presentation.primary.views.pager_views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.base.views.BaseButton
import com.example.core.base.views.BaseCardView
import com.example.core.base.views.ExtraSmallHorizontalSpacer
import com.example.core.base.views.HorizontalSpacerColorView
import com.example.core.utils.extensions.modifiers.defaultTextPadding
import com.example.core.utils.extensions.modifiers.smallPadding
import com.example.theme.R
import com.example.theme.WhiteBoldTextStyle

@Preview
@Composable
fun SimpleModeViewPreview() {
    SimpleModeView()
}

@Composable
fun SimpleModeView() {
    BaseCardView {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            ExtraSmallHorizontalSpacer()
            Text(
                text = stringResource(id = R.string.simple_mode),
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultTextPadding(),
                style = WhiteBoldTextStyle,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseButton(
                    text = "Button - 1",
                    buttonModifier = Modifier
                        .weight(1f)
                        .smallPadding()
                ) {}
                BaseButton(
                    text = "Button - 2",
                    buttonModifier = Modifier
                        .weight(1f)
                        .smallPadding()
                ) {}
            }
            ExtraSmallHorizontalSpacer()
            HorizontalSpacerColorView(colorResource(id = R.color.white))
            ExtraSmallHorizontalSpacer()
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseButton(
                    text = "Button - 3", buttonModifier = Modifier
                        .weight(1f)
                        .smallPadding()
                ) {}
                BaseButton(
                    text = "Button - 4", buttonModifier = Modifier
                        .weight(1f)
                        .smallPadding()
                ) {}
            }
            ExtraSmallHorizontalSpacer()
        }
    }
}