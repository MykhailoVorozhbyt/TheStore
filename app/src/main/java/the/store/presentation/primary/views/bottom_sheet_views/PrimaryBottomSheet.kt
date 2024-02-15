package the.store.presentation.primary.views.bottom_sheet_views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.entities.SaleHistoryEntity
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.ui.widget.EmptyListView
import com.example.core.utils.extensions.modifiers.smallHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallPadding
import com.example.core.utils.extensions.modifiers.smallStartPadding
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.WhiteBoldTextStyle
import com.example.theme.WhiteTextStyle
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import the.store.utils.convertToDate
import the.store.utils.workerItemRoundedCorner

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PrimaryBottomSheetContentPreview() {
    PrimaryBottomSheetContent(
        list = listOf(
            SaleHistoryEntity(
                id = 0,
                saleId = 1L,
                createdAt = 1707916401514L,
                fullPrice = 155.0,
                products = 11
            )
        ),
        historySearch = "1",
        searchText = {},
        itemClick = {}
    )
}

@Composable
fun PrimaryBottomSheetContent(
    list: List<SaleHistoryEntity>,
    historySearch: String,
    searchText: (String) -> Unit,
    itemClick: (Long) -> Unit
) {
    Column {
        InputTextField(
            onValueChange = { resultText ->
                searchText.invoke(resultText)
            },
            textValue = historySearch,
            columnModifier = Modifier.smallHorizontalPadding()
        )
        if (list.isEmpty()) {
            EmptyListView()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(list) { index, item ->
                    HistoryItem(
                        item,
                        index == 0,
                        index == list.size - 1
                    ) { id ->
                        itemClick.invoke(id)
                    }
                }
            }
        }

    }
}

@Composable
fun HistoryItem(
    data: SaleHistoryEntity,
    isFirsItem: Boolean,
    isLastITem: Boolean,
    click: (Long) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                click.invoke(1L)
            }
            .smallHorizontalPadding()
            .padding(vertical = 1.dp)
            .clip(workerItemRoundedCorner(isFirsItem, isLastITem))
            .background(TheStoreColors.blackOrWhiteColor)
            .smallPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = rememberVectorPainter(Icons.Filled.Star),
            contentDescription = null,
            tint = TheStoreColors.whiteOrBlackColor
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .smallStartPadding()
        ) {
            Text(
                text = stringResource(id = R.string.check),
                style = WhiteBoldTextStyle
            )
            Text(
                text = stringResource(id = R.string.fiscal_id_and_value, data.id),
                style = WhiteTextStyle,
                maxLines = 2
            )
            Text(
                text = stringResource(id = R.string.time_and_value, data.createdAt.convertToDate()),
                style = WhiteTextStyle,
                maxLines = 2
            )
        }
        Icon(
            painter = rememberVectorPainter(Icons.Default.KeyboardArrowRight),
            contentDescription = null,
            tint = TheStoreColors.whiteOrBlackColor
        )
    }
}