package the.store.presentation.primary.views.bottom_sheet_views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.core.domain.db_entity.check.CheckProductDbEntity
import com.example.core.domain.entities.SaleHistoryEntity
import com.example.core.domain.utils.Currency.Companion.getCurrencyById
import com.example.core.domain.utils.Measurements.Companion.getMeasurementById
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
import the.store.presentation.primary.PrimaryScreenPreviewProvider
import the.store.utils.convertToDate
import the.store.utils.workerItemRoundedCorner

@Composable
fun PrimaryBottomSheetContent(
    list: List<SaleHistoryEntity>,
    historySearch: String,
    searchText: (String) -> Unit,
    itemClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
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
    val isCollapsed = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .smallHorizontalPadding()
            .padding(vertical = 1.dp)
            .clip(workerItemRoundedCorner(isFirsItem, isLastITem))
            .background(TheStoreColors.blackOrWhiteColor)
            .smallPadding(),
    ) {
        Row(
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
                    text = stringResource(
                        id = R.string.time_and_value,
                        data.createdAt.convertToDate()
                    ),
                    style = WhiteTextStyle,
                    maxLines = 2
                )
            }
            IconButton(onClick = {
                isCollapsed.value = !isCollapsed.value
                click.invoke(data.id)
            }) {
                Icon(
                    painter = rememberVectorPainter(if (isCollapsed.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown),
                    contentDescription = null,
                    tint = TheStoreColors.whiteOrBlackColor
                )
            }
        }
        if (isCollapsed.value) {
            data.products.forEach {
                ProductItem(it)
            }
        }
    }
}

@Composable
private fun ProductItem(product: CheckProductDbEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 2.dp)
                .height(1.dp)
                .background(TheStoreColors.whiteOrBlackColor)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "${stringResource(id = R.string.name)}: ${product.name}",
                modifier = Modifier.weight(1f),
                style = WhiteTextStyle,
            )
            Text(
                "${stringResource(id = R.string.price)} ${product.price}",
                style = WhiteTextStyle,
            )
            Icon(
                painter = painterResource(getCurrencyById(product.currencyId.toLong()).iconId),
                contentDescription = null,
                tint = TheStoreColors.whiteOrBlackColor,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "${stringResource(id = R.string.price)}: ${product.fullPrice}",
                modifier = Modifier.weight(1f),
                style = WhiteTextStyle,
            )
            Text(
                stringResource(R.string.full_price_with_value, product.quantity),
                style = WhiteTextStyle,
            )
            Icon(
                painter = painterResource(getMeasurementById(product.measurementId.toLong()).iconId),
                contentDescription = null,
                tint = TheStoreColors.whiteOrBlackColor,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }

}

@PreviewLightDark
@Composable
fun PrimaryBottomSheetContentPreview() {
    PrimaryBottomSheetContent(
        list = PrimaryScreenPreviewProvider.getSaleHistoryEntity(),
        historySearch = "1",
        searchText = {},
        itemClick = {}
    )
}
