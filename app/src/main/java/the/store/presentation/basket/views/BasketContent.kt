package the.store.presentation.basket.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.example.core.domain.entities.ProductEntity
import com.example.core.ui.custom_composable_view.InputTextField
import com.example.core.ui.widget.EmptyListView
import com.example.core.ui.widget.ProgressIndicator
import com.example.core.utils.extensions.modifiers.baseRoundedCornerShape
import com.example.core.utils.extensions.modifiers.basketListIconSize
import com.example.core.utils.extensions.modifiers.defaultHorizontalPadding
import com.example.core.utils.extensions.modifiers.defaultListIconSize
import com.example.core.utils.extensions.modifiers.defaultTextStartPadding
import com.example.core.utils.extensions.modifiers.defaultTopPadding
import com.example.core.utils.extensions.modifiers.smallHorizontalPadding
import com.example.core.utils.extensions.modifiers.smallPadding
import com.example.theme.R
import com.example.theme.TheStoreColors
import com.example.theme.WhiteBoldTextStyle
import com.example.theme.WhiteTextStyle
import com.example.theme.blackOrWhiteColor
import com.example.theme.whiteOrBlackColor
import the.store.presentation.products.models.productList
import the.store.utils.TOperation
import the.store.utils.imageRequestBuilder
import the.store.utils.workerItemRoundedCorner

@PreviewLightDark
@Composable
fun BasketContentPreview() {
    BasketContent(productList())
}


@Composable
fun BasketContent(
    list: List<ProductEntity>
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        InputTextField(
            onValueChange = { resultText ->
//                searchText.invoke(resultText)
            },
            textValue = "",
            columnModifier = Modifier
                .defaultHorizontalPadding()
                .defaultTopPadding()
        )
        if (list.isEmpty()) {
            EmptyListView()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .smallHorizontalPadding()
            ) {
                itemsIndexed(list) { index, item ->
                    ProductContentItem(
                        context,
                        item,
                        index == 0,
                        index == list.size - 1
                    ) {
//                        productClick.invoke(it)
                    }
                }
            }
        }

    }
}

@PreviewLightDark
@Composable
fun ProductContentItemPreviewLightDark() {
    ProductContentItem(
        LocalContext.current,
        ProductEntity(
            id = 0,
            photoUri = "222",
            name = "Misha",
            1500.0,
            R.string.kilogram,
            R.string.usd
        ),
        true,
        false
    ) {}
}

@Composable
fun ProductContentItem(
    context: Context,
    product: ProductEntity,
    isFirsItem: Boolean,
    isLastITem: Boolean,
    productClick: TOperation<Long>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 1.dp)
            .clip(workerItemRoundedCorner(isFirsItem, isLastITem))
            .background(TheStoreColors.blackOrWhiteColor)
            .clickable { productClick(product.id) }
            .smallPadding(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val photoUri = product.photoUri
        if (photoUri.isNullOrBlank()) {
            Icon(
                rememberVectorPainter(Icons.Filled.Person),
                contentDescription = null,
                tint = TheStoreColors.whiteOrBlackColor,
                modifier = Modifier.basketListIconSize()
            )
        } else {
            val painter = imageRequestBuilder(
                context,
                photoUri,
                R.drawable.ic_person
            )
            if (painter.state is AsyncImagePainter.State.Loading) {
                ProgressIndicator(
                    color = TheStoreColors.whiteOrBlackColor,
                    modifier = Modifier.basketListIconSize()
                )
            } else {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = TheStoreColors.whiteOrBlackColor,
                            shape = baseRoundedCornerShape()
                        )
                        .basketListIconSize()
                        .clip(baseRoundedCornerShape())
                )
            }

        }
        Column(
            modifier = Modifier
                .defaultTextStartPadding()
                .weight(1f)
        ) {
            Text(
                text = product.name,
                minLines = 1,
                style = WhiteBoldTextStyle
            )
            Text(
                text = stringResource(R.string.price_with_value, product.price.toString()),
                minLines = 1,
                style = WhiteTextStyle
            )
            val measurementAndCurrency =
                stringResource(product.measurementResId) + " | " + stringResource(product.currencyResId)
            Text(
                text = measurementAndCurrency,
                minLines = 1,
                style = WhiteTextStyle
            )
        }
        Icon(
            rememberVectorPainter(Icons.Filled.KeyboardArrowRight),
            contentDescription = null,
            tint = TheStoreColors.whiteOrBlackColor,
            modifier = Modifier
        )
    }
}