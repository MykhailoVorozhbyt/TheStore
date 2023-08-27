package the.store.presentation.primary.pager_views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.base.BaseButton
import com.example.core.ui.base.HorizontalSpacerColorView
import com.example.core.ui.base.SmallSpacer
import com.example.core.utils.extensions.modifiers.defaultCardPadding
import com.example.theme.Black
import com.example.theme.R
import com.example.theme.White

@Preview
@Composable
fun SimpleModeViewPreview() {
    SimpleModeView()
}

@Composable
fun SimpleModeView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultCardPadding(),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Simple mode",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                BaseButton(
                    text = "text - 1",
                    modifier = Modifier.size(100.dp)
                ) {}
                Divider(
                    color = Black,
                    modifier = Modifier.fillMaxHeight().width(1.dp)
                )
                BaseButton (
                        text = "text - 2",
                modifier = Modifier.size(100.dp)
                ) {}
            }
            SmallSpacer()
            HorizontalSpacerColorView(colorResource(id = R.color.black))
//            Row(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                BaseButton(text = "text - 3") {}
//                VerticalSpacerColorView(colorResource(id = R.color.black))
//                BaseButton(text = "text - 4") {}
//            }
        }
    }
}