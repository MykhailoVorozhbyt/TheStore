package the.store.presentation.basket.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.core.base.views.BaseButton
import com.example.core.utils.extensions.modifiers.defaultVerticalPadding
import com.example.core.utils.extensions.modifiers.smallEndPadding
import com.example.core.utils.extensions.modifiers.smallHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallPadding
import com.example.core.utils.extensions.modifiers.smallTopPadding
import com.example.theme.BlackBoldTextStyle
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.blackOrWhiteColor
import the.store.presentation.products.models.productList

val BasketSheetContentData = BasketSheetContent(
    15
)

@PreviewLightDark
@Composable
fun BasketSheetContentPreview() {
    BasketSheetContent(
        BasketSheetContentData
    )
}


data class BasketSheetContent(
    val productsInTheBasket: Int
)

@Composable
fun BasketSheetContent(
    data: BasketSheetContent
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .smallPadding()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_shopping_basket),
                contentDescription = null,
                tint = TheStoreColors.blackOrWhiteColor,
                modifier = Modifier.smallEndPadding()
            )
            Text(
                stringResource(
                    R.string.products_in_the_basket_with_value,
                    data.productsInTheBasket
                ),
                style = BlackBoldTextStyle
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .smallTopPadding()
                .weight(1f)
        ) {
            itemsIndexed(productList()) { index, item ->
                ProductContentItem(
                    context,
                    item,
                    index == 0,
                    index == productList().size - 1
                ) {
//                        productClick.invoke(it)
                }
            }
        }

        BaseButton(
            text = stringResource(id = R.string.sell),
            onClick = {
            },
            buttonModifier = Modifier
                .fillMaxWidth()
                .smallHorizontalPadding()
                .defaultVerticalPadding()
        )
    }
}