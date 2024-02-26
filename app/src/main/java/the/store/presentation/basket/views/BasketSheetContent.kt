package the.store.presentation.basket.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.core.base.views.BaseButton
import com.example.core.domain.entities.CheckProductEntity
import com.example.core.utils.extensions.modifiers.baseBottomRoundedCornerShape
import com.example.core.utils.extensions.modifiers.baseTopEndCornerShape
import com.example.core.utils.extensions.modifiers.baseTopStartCornerShape
import com.example.core.utils.extensions.modifiers.defaultVerticalPadding
import com.example.core.utils.extensions.modifiers.smallEndPadding
import com.example.core.utils.extensions.modifiers.smallHorizontalPadding
import com.example.theme.BlackBoldTextStyle
import com.example.theme.BlackTextStyle
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import the.store.presentation.basket.models.CheckProductEntityList
import the.store.utils.TOperation
import the.store.utils.UnitOperation

@PreviewLightDark
@Composable
fun BasketSheetContentPreview() {
    BasketSheetContent(
        CheckProductEntityList(), {}, {}, {}, {}, {}
    )
}

@Composable
fun BasketSheetContent(
    list: List<CheckProductEntity>,
    salleClick: UnitOperation,
    deleteAllProductsClick: UnitOperation,
    plusQuantity: TOperation<Long>,
    minusQuantity: TOperation<Long>,
    deleteProductClick: TOperation<Long>,
) {
    Column(
        modifier = Modifier
            .background(TheStoreColors.whiteOrBlackColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_shopping_basket),
                contentDescription = null,
                tint = TheStoreColors.whiteOrBlackColor,
                modifier = Modifier.smallEndPadding()
            )
            Text(
                stringResource(
                    R.string.products_in_the_basket_with_value,
                    list.size.toString()
                ),
                style = BlackBoldTextStyle
            )
        }

        if (list.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.remove_all_products),
                    style = BlackBoldTextStyle
                )
                IconButton(onClick = { deleteAllProductsClick.invoke() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = null,
                        tint = TheStoreColors.blackOrWhiteColor,
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(TheStoreColors.blackOrWhiteColor)
            ) {
                items(list) { item ->
                    BasketSheetItem(
                        data = item,
                        plusQuantity = plusQuantity,
                        minusQuantity = minusQuantity,
                        deleteProductClick = deleteProductClick,
                    )
                }
            }

            BaseButton(
                text = stringResource(id = R.string.sell),
                onClick = {
                    salleClick.invoke()
                },
                buttonModifier = Modifier
                    .fillMaxWidth()
                    .smallHorizontalPadding()
                    .defaultVerticalPadding()
            )
        }
    }
}

@PreviewLightDark
@Composable
fun BasketSheetItemPreview() {
    BasketSheetItem(
        CheckProductEntity(
            id = 0,
            photoUri = "211",
            name = "Product 1",
            price = 100.0,
            fullPrice = 100.0 * 4,
            quantity = 4.0,
            measurementResId = R.string.kilogram,
            currencyResId = R.string.usd
        ),
        plusQuantity = {},
        minusQuantity = {},
        deleteProductClick = {},
    )
}

@Composable
fun BasketSheetItem(
    data: CheckProductEntity,
    plusQuantity: TOperation<Long>,
    minusQuantity: TOperation<Long>,
    deleteProductClick: TOperation<Long>,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .padding(horizontal = 2.dp)
            .fillMaxWidth()
            .background(TheStoreColors.whiteOrBlackColor)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1F)
        ) {
            Text(
                text = data.name,
                minLines = 1,
                style = BlackBoldTextStyle
            )
            Text(
                text = stringResource(R.string.price_with_value, data.price.toString()),
                minLines = 1,
                style = BlackTextStyle
            )
            val measurementAndCurrency =
                stringResource(data.measurementResId) + " | " + stringResource(data.currencyResId)
            Text(
                text = measurementAndCurrency,
                minLines = 1,
                style = BlackTextStyle
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.5F)
        ) {
            Row {
                IconButton(
                    onClick = { plusQuantity.invoke(data.id) },
                    modifier = Modifier
                        .padding(1.dp)
                        .baseTopStartCornerShape()
                        .background(TheStoreColors.blackOrWhiteColor)
                        .weight(1f)
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        tint = TheStoreColors.whiteOrBlackColor
                    )
                }

                IconButton(
                    onClick = { minusQuantity.invoke(data.id) },
                    modifier = Modifier
                        .padding(1.dp)
                        .baseTopEndCornerShape()
                        .background(TheStoreColors.blackOrWhiteColor)
                        .weight(1f)
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_remove),
                        contentDescription = null,
                        tint = TheStoreColors.whiteOrBlackColor
                    )
                }
            }
            IconButton(
                onClick = { deleteProductClick.invoke(data.id) },
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                    .baseBottomRoundedCornerShape()
                    .background(TheStoreColors.blackOrWhiteColor)

            ) {
                Icon(
                    painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    tint = TheStoreColors.whiteOrBlackColor
                )
            }
        }
    }
}